---
title: "Callback Pattern in Node.js"
date: 2018-10-30
permalink: /posts/2018/10/2018-10-30-callback-pattern-nodejs/
tags:
 
excerpt: Understand Callback Patterns and Use Them

---

## What are Callback Functions
Callback functions are derived from a programming paradigm known as functional programming. Functional programming was—and still is, though to a much lesser extent today—seen as an esoteric technique of specially trained, master programmers.

Fortunately, the techniques of functional programming have been elucidated so that mere mortals like you and me can understand and use them with ease. Callback functions is one of them which is very easy to understand and very beneficial to use.

>_A Callback can be defined as a function that is passed as an invocation argument to another function or method and is expected to be executed at some later point of time_

doesn’t make any sense..!! function passing to another function…!! what’s going on here..?? i know, a bit difficult to understand isn't it.

Let's try to simplify it using normal English analogy. 
Let's say you have to check with one of your friend if he is available for dinner. You would probably leave a message like this

_"Hey, Paul. Please **call** me **back** when you decide whether or not you're joining me over dinner."_

The implication is that Paul can't answer yet. But when he has finally decided, you might not be around to ask him the question. So I say, "Please answer this question when you can." A callback is a way of asking a question (or requesting a task) in advance.

``` js
button.addEventListener('click', foo);

function foo() {
    // ... do some usefull stuff
}
```
You don't know when the user will click the button. But whenever he does, you need a function to be called. 


## Why Callbacks?
Node.js is full of callbacks, and they exist because of asynchronous nature of Node.js. 

The cool thing about asynchronous programming is that while your code waits for something to be done (like an API call or a response from a database) it can do something else. That means, your code is non-blocking.

Synchronously running code can spend lot of time waiting. If applications can process requests while they are waiting for I/O, stuff gets done faster.

I must say things do feel a little more complex with asynchronous programming, especially when you begin, but it’s actually not that hard of a concept to grasp and the benefits are worth it.

```js
// Synchronous Blocking code
function doSomething() {
    let hello = fetchHelloFromRemoteDB();
    return hello + "World"
}
```

In the above code we are trying to fetch data from remote DB and we are waiting for that function to be completed before we do the next step

In Asynchronous world it would be done in something like this
```js
// Asynchronous
function processData(callBack) {   
    fetchHelloFromRemoteDB(function (err, data) {     
        if (err) {
           return callback(err);
        }     
        let dataToBeReturned = data + "World"
        callback(null, dataToBeReturned);   
    }); 
}
```

## Confusion when using Callback
When you are starting with Node.js and you are new to asynchronous stuff , it is very common to get confused with callbacks and how Node.js works everything in **async** way. For example

```js
let file = fs.readFile(fooFileName);
console.log("file: ", file);
```
In the above code ```file``` will be undefined. Reason for that is ```**async**```

How it works in Node.js is
```js
fs.readFile(funFileName, callback)

function callback(err, file) {
    if(err)
        handleError(err);
    console.log("file: ", file)
})
```
When ```fs.readFile``` is done fetching the file , it executes the callback function, which handles the error if an error is thrown and logs the retrieved file to the console.

## Some Points to take care when implementing Callback Functions
While uncomplicated, callback functions have a few noteworthy principles we should be familiar with when implementing them.

+ We can use Named OR Anonymous Functions as Callbacks
+ We can Pass Parameters to Callback Functions
+ Problem When Using Methods With The this Object as Callbacks
+ Chaining Callback Functions is Allowed
+ The first argument of the callback is reserved for an error object.
+ The second argument of the callback is reserved for any successful response data


## Conclusion
Callbacks are great but exercise bit of caution when using it. Primarily they are to be used when we don’t know when something will be done. Again, think of something like an API call, fetching data from a database or I/O with the hard drive. All of these will take time, so we want our callback to be called when the event we are waiting for is done.

## Coming soon…
If you’re starting out with callbacks you’ll quickly bump into promises, you should probably make sure you really get callbacks before you jump on promises, but you also should probably get to know promises some day. So stay tuned to make sure you catch the upcoming article about Promises


+ [Back To Advanced Node.js](/posts/2018/10/2018-10-30-advanced-nodejs/)