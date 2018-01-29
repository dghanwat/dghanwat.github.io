---
title: "Pattern Matching"
collection: Training
type: "chapter"
permalink: /teaching/scala/pattern_matching
venue: "Online"
date: 2016-02-23
location: "Pune, India"

---
# Pattern Matching
---
Scala has a built-in general pattern matching mechanism
It allows to match on any sort of data with a first-match policy
---
![alt ](./images/pattern-matching-1.png)
---
*   Here is a second example which matches a value against patterns of different types:
![alt ](./images/pattern-matching-2.png)
---
## Enumeration
*   Enumeration allows programmer to define their own data type
*   Often we have a variable that can take one of several values. For instance, a WeekDays field of an object could be  either Mon, Tue, Wed, or Thu
![alt ](./images/enum-1.png)
---
### Enumeration (cont'd)
*   Another way
![alt ](./images/enum-2.png)
*   Gives Error if value is not found
![alt ](./images/enum-3.png)
