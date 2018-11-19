---
title: "Promises in Node.js"
date: 2018-11-18
permalink: /posts/2018/11/2018-11-18-promises-nodejs/
tags:
 
excerpt: Using Promises in Node.js

---

While synchronous code is easier to follow and debug, async is generally better for performance and flexibility. Why "hold up the show" when you can trigger numerous requests at once and then handle them when each is ready?  Promises are becoming a big part of the Node.js world, with many new APIs being implemented with the promise philosophy. 

## Introduction

The A+ specification defines a promise this way
>_A promise represents the eventual result of an asynchronous operation. The primary way of interaction with a promise is through its then method, which registers callbacks to receive either a promise’s eventual value or the reason why the promise cannot be fulfilled._

Its fine if that didn't make any senses. The concept of a JavaScript promise is better explained through an analogy, so let’s do just that to help make the concept clearer.

Imagine you’re preparing for a birthday party for your a friend next week. As you speak about the party, a common friend, Sam, offered to help. Delighted, you asked him to buy a black forest birthday cake. Sam said okay.

Here, Sam has given you his word that he’ll buy you a black forest birthday cake. It’s a **promise**. The code version of scenario can be written in the following way:

```js
const promise = samBuysCake('black forest')
```

You begin to plan your next steps. You realize that you can carry on the party as planned if Sam keeps to his words and buys you a black forest cake in time for the party.

If Sam does keep his promise and buy the cake, we say the promise is ```resolved``` . When a promise gets resolved, you do the next thing in a ```.then``` call:

```js
samBuysCake('black forest')
  .then(partyAsPlanned) // Woohoo! 🎉🎉🎉
```

But if he doesn’t buy you the cake, you got to run to the bakery yourself. (Damn you, Sam!). If this happens, we say the promise is ```rejected```.

When a promise gets rejected, you do your contingency plan in a ```.catch``` call.

```js
samBuysCake('black forest')
  .then(partyAsPlanned)
  .catch(rushToBakery) // Grumble Grumble... #*$%
```

This, my friend, is an anatomy of a Promise.

## Constructing a promise
You can make a promise by using ```new Promise```. This Promise constructor takes in a function that contains two arguments — ```resolve``` and ```reject```.

``` js
const promise = new Promise((resolve, reject) => {
  /* Do something here */
})
```

If resolve is called, the promise succeeds and continues into the then chain. The parameter you pass into resolve would be the argument in the next then call:

``` js
const promise = new Promise((resolve, reject) => {
  // Note: only 1 param allowed
  return resolve("success")
})


// Parameter passed resolve would be the arguments passed into then.
promise.then(response => console.log(response)) 
```

If reject is called, the promise fails and continues into the catch chain. Likewise, the parameter you pass into reject would be the argument in the catch call.

``` js
const promise = new Promise((resolve, reject) => {
  // Note: only 1 param allowed
  return reject('error')
})

// Parameter passed into reject would be the arguments passed into catch.
promise.catch(err => console.log(err)) 
```

Not too hard to make a promise, isn’t it? 😉.

## Promise.all
Let’s say you have multiple async requests you want to fire off in parallel and then once they are done you want to use their results to do some additional processing. In the callback world you would have to maintain a variable that keeps track of which requests have returned and which haven’t. You will also need a data structure that would contain the results (and potentially in the order in which the requests were called). While this is doable with callbacks, it’s unnecessarily complex and error-prone, especially when you also need to do error handling.

The ```Promise.all``` method makes this significantly easier. It takes an array of promises as a parameter and returns a ```Promise``` that will be fulfilled with an array of response values only when all of the promises are fulfilled.

If any of the promises are rejected (i.e. fail), then the aggregator ```Promise``` from ```Promise.all``` also is rejected. You can use the catch() reaction to catch these failures.

``` js
// Return a promise that is only fulfilled once
// all of the url fetch requests are fulfilled
// via Promise.all
function fetchAll(...urls) {
	// Use rest parameter to aggregate URLs
	// into an array

	return Promise.all(
		// map the array of urls into an array
		// of `fetch` promises
		urls.map(fetch)
	);
}

// Make an XHR request for each URL and
// process the results once they've *all*
// completed
fetchAll(
	'/json/data.json',
	'/json/data2.json',
	'/json/data3.json',
	'/json/data4.json',
	'/json/data5.json'
)
    .then(responses => {
    	// `responses` is the array of response
    	// data

    	// output: 5
    	console.log(responses.length);

    	// more processing of results
    })
    .catch(e => {
    	// one or more of the requests failed
    	// or there was an error in `then()`
    	console.error(e);
    });
```    

## Conclusion

Promises are good for async operations that happen one time and then they’re done. You shouldn’t use promises for recurring events (such as clicks, key presses, etc.). If you want an alternative to normal event handling for recurring events, try out reactive programming in JavaScript.

While the Promise API based on the Promises/A+ standard is crazy powerful, it’s still lacking some useful functionality that exist in some of our existing promise libraries.

The first omission is the ability to cancel a Promise. This could come in handy when using Promise.all. If one promise’s completion causes the result of others to no longer matter, it’d be nice to be able to cancel them. The cancellation spec is already under development for Promises/A+.

The second omission is the ability to query the progress of a Promise. Imagine making a fetch call of a 100MB file. Being a able to display a progress indicator would be pretty helpful. Thankfully, the progress spec too is under development for Promises/A+.

## Coming Soon
Promises give us a nice way to organize what happens by chaining together ```.then``` handlers. We use this technique to compose promise chains that can execute sequentially without blocking them. But what if you don't want to compose functions in a chain? What happens when you just want to write code? All we really need to do is wait for the result of one promise to complete before running some more code. Do we always have to encapsulate code bits in tiny functions? The answer is no
Stay tuned to this series to learn about **Sequential execution with async/await**

+ [Back To Advanced Node.js](/posts/2018/10/2018-10-30-advanced-nodejs/)
+ [Callback Pattern in Node.js](/posts/2018/10/2018-10-30-callback-pattern-nodejs/)
