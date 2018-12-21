---
title: "Chat Bot with Node.JS, Express and RabbitMQ Part 1"
date: 2018-12-15
permalink: /posts/2018/12/chatbot-with-nodejs-express-rabbitmq-part-1/
tags:
 
excerpt: Lets build a highly scalable, distributed chatbot service with Node.JS, Express and RabbitMQ

---

This is the first part of building a distributed, scalable and event driven chatbot service using Node.JS, Express and RabbitMQ. The application will implement the below design where we expose a chat bot microservice, which will forward the request to a RabbitMQ queue and answer the user's query using our bot developed using [ChatterBot](https://chatterbot.readthedocs.io/en/stable/)

Details of ChatterBot is outside the scope of this write up, but you can have a look at it at their site to learn more.

![Distributed Chatbot](/images/bot-design.png "Distributed Chatbot")

Its a good example of how we can combine HTTP world, which is of type Request - Response with Event Driven world, which is of the type Fire and Forget world. Its not very often that we need this integration, but there could be some use cases like the one we are building or to be more general, any use case where you have a long running process, but still need to response the user. 

It shouldn't be a long series. Let's see what we can build.

In this part we will start with Express part and integration with RabbitMQ.

Let's get our environment working. First, we'll need to install the libraries that we'll use.

Create a dir and initialize the nodejs application inside it (```npm init```) and then let's install the deps.

```javascript
npm install --save express http body-parser http-errors cookie-parser normalize-port rabbot
```

Now let's write the entry points for our service, we'll have one entry point. The path /chat that will handle the request and produce a message for our RabbitMQ service

```javascript
var express = require("express");
var createError = require('http-errors');
var cookieParser = require('cookie-parser');
var bodyParser = require("body-parser");
var log = require('morgan');
const http = require('http');
const normalizePort = require('normalize-port');
var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(log('dev'));
app.use(cookieParser());
app.use(express.static('public'))

var port = normalizePort(process.env.PORT || '3000');

app.get('/hello', (req, res) => res.send('Hello From Chat Bot service'));

app.post('/chat', function (request, response) {
    response.send('Not ready yet');
});


// error handler
app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};
    // render the error page
    res.status(err.status || 500);
    res.json({
        'error': err.message
    });
});

var server = app.listen(port, function () {
    console.log("app running on port.", server.address().port);
});

module.exports = server
```

Let's check if it works, run the service with node.

```javascript
node index.js
app running on port 3000!
```

Open the browser and check if it prints our hello message when accessing localhost:4000/hello. Works? Great. 

Now lets try to integrate with RabbitMQ. For that first we need to define some configuration for RabbitMQ. Create a folder rabbitmq and lets create some RabbitMQ configuration.

Create a file ```config.js```
```javascript
var rabbitConfig = {
  connection: {
    name: "rabbitmqcon",
    user: "guest",
    pass: "guest",
    server: "192.168.99.100",
    vhost: "/",
    port: "5672",
    heartbeat: 20,
    replyQueue: false,
  },
  exchanges: [
    {
      name: "chat_request_exchange",
      type: "fanout",
      durable: true,
    },
    {
      name: "chat_response_exchange",
      type: "fanout",
      durable: true,
    }
  ]
};

module.exports = rabbitConfig
```


Create a file ```queues.js```
```javascript
var  queues = [
  {
    name: "chat_request_queue",
    vhost: "/",
    durable: false,
    autoDelete: true,
    arguments: {
      "x-message-ttl": 100000,
    },
    subscribe: false,
    unique: "id",
  },
  {
    name: "chat_response_queue",
    vhost: "/",
    durable: false,
    autoDelete: true,
    arguments: {
      "x-message-ttl": 100000,
    },
    subscribe: false,
    unique: "id",
  }

];

module.exports = queues

```

Create file ```bindings.js```
```javascript
var bindings = [
    {
      exchange: "chat_request_exchange",
      target: "chat_request_queue",
    }, {
      exchange: "chat_response_exchange",
      target: "chat_response_queue",
    }
  ];

  
module.exports = bindings
```

The above files are simple configuration files, which basically define two exchanges and two queues and then binds the exchanges to queues. Our Express service will write the incoming message to ```chat_request_exchange``` and listen to ```chat_response_queue``` for the Response from ChatterBot Service

Now that we have definitions of our RabbitMQ environment, lets update our ```index.js``` to apply these configurations

```javascript
var queues = require("./rabbitmq/queues");
var bindings = require("./rabbitmq/bindings");
var rabbitConfig = require("./rabbitmq/config");
const rabbit = require("rabbot");

const INCOMING_QUEUE = 'chat_response_queue';
const REQUEST_EXCHANGE = "chat_request_exchange";
rabbit.handle({}, handleMessage); // Handle the incoming message
rabbit.configure(rabbitConfig);

rabbit.on(rabbitConfig.connection.name + ".connection.opened", (c) => {
    console.log("RabbitMq: Connection " + rabbitConfig.connection.name + " opened");
    queues.forEach((q, index) => {
        if (!rabbit.getQueue(q.name, rabbitConfig.connection.name)) {
            rabbit.addQueue(q.name, q, rabbitConfig.connection.name).then((s) => {
                rabbit.bindQueue(bindings[index].exchange, bindings[index].target, "",
                    rabbitConfig.connection.name);
                    
            });
        } else {
            console.log("RabbitMq: Connection Queue already exists");
        }
    });
});

rabbit.on(rabbitConfig.connection.name + ".connection.closed", () => {
    console.log("RabbitMq: Connection " + rabbitConfig.connection.name + " closed");
});

rabbit.on(rabbitConfig.connection.name + ".connection.failed", (c) => {
    console.log("RabbitMq: Connection " + rabbitConfig.connection.name + " failed");
    queues.forEach((q, index) => {
        if (rabbit.getQueue(q.name, rabbitConfig.connection.name)) {
            rabbit.stopSubscription(q.name, rabbitConfig.connection.name);
            rabbit.deleteQueue(q.name, rabbitConfig.connection.name);
        }
    });
});

rabbit.on(rabbitConfig.connection.name + ".connection.configured", (connection) => {
    Object.entries(connection.definitions.bindings).forEach(([key, value]) => {
        console.log(`RabbitMq: Queue ${value.target} bound to exchange ${value.source}`);
    });
    rabbit.startSubscription(INCOMING_QUEUE, rabbitConfig.connection.name);
});

```

Update our endpoint code with the following lines

```javascript
app.post('/chat', function (request, response) {
    rabbit.publish(REQUEST_EXCHANGE, {
        contentType: "application/json",
        body:input
    },rabbitConfig.connection.name);

     response.send('Not ready yet');
});
```

Use some REST client like Postman to test the request. To see if it will work, make a request to http://localhost:3000/chat with JSON Body as 
```json
{
    "message":"Hello"
}
```

If everything has setup fine, you should see a message on the Rabbit MQ queue ```chat_request_queue```

So we have achieved the first of the processing, where we have succesfully published the message on the RabbitMQ.

Now lets write the part of the code, where we will handle the response from the RabbitMQ.
For doing that we will use the ```co-relationid``` property of RabbitMQ message and ```EventEmitter``` object from Node.JS

Update your index.js file with following lines

```javascript
var events = require("events");
var eventEmitter = new events.EventEmitter();


//Random id generator
function randomid() {
    return new Date().getTime().toString() + Math.random().toString() + Math.random().toString();
}

app.post('/chat', function (request, response) {
    console.log(request.body)
    response.type('application/json');
    input = {
        "message" : "Hello"
    }

    let id = randomid();

    //Define an event listener such that it will fire exactly once, when we get a message with same correlation Id.
    eventEmitter.once(id, msg => {
        response.status(200).send(msg)
    });

    rabbit.publish(REQUEST_EXCHANGE, {
        correlationId: id, // Set a unique Correlation Id for each request.
        contentType: "application/json",
        body:input
    },rabbitConfig.connection.name);

});

// Handle message is invoked every time a 
// message is received on the RabbitMQ queue
function handleMessage(message) {
    // Emit a new event with Correlation Id as received from the incoming message.
    // Note we have already defined an event handler which will handle the response when the correlation id matches
    eventEmitter.emit(message.properties.correlationId, message.body)
}
```

That's it for today, in the next post we will see how to write a chatter Bot Service which will listen to the ```chat_request_queue``` and write the message to ```chat_response_exchange``` for which we have already defined the handler

