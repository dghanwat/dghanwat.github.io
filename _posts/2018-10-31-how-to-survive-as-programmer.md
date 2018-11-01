---
title: "How to survive as a programmer"
date: 2018-10-30
permalink: /posts/2018/10/how-to-survive-as-programmer/
tags:
 
excerpt: Programming is fun and here are some tips gathered over 18 years of programming experience on how to survive (and excel) as a programmer

---

![Core Values](/images/programming.jpg "Core Values")

> Everybody in this country should learn to program a computer...
> because it teaches you how to think
>
>> <cite>Steve Jobs</cite>

I have always enjoyed writing code and I still do. I think it is not because I am good at it, but it is such a challenge. One of the first program which I wrote was, when I was in grade five, was to find if a given word is a [palindrome](https://en.wikipedia.org/wiki/Palindrome) or not and after that I have written thousands of lines of code. 

As much as I enjoy programming, I must in all honesty admit that I am a mediocre programmer who has always found a way to get to the big payoff. Today I am going to share some experiences which I have gained over past eighteen years of on how you can survive in this industry even if you are not some rockstar developer

## A developer must wear many hats
Programmer is but one of the many roles of a developer; you often have to wear the following hats as well:

+ Analyst
+ Designer
+ Planner
+ Programmer
+ Coordinator
+ Tester
+ Documenter
+ Support technician
+ Operational Engineer

Even with my less than stellar programming skills, I am very successful as a developer. The following is how I survived to make the best of my average coding skills:

### You don't have to be programming encyclopaedia
You don't have to remember everything, you just need to be **AWARE** that there's such function or such class or such feature. 

I fail to remember a lot of the things. Like functions and methods from the standard library, arguments positions, package names, boilerplate code and so on.

So, I have to google it. And I do it on a daily basis. I also reuse code from old projects. Sometimes I even copy-paste answers from StackOverflow or Github. However, the trick to survive here is to be smart and make your own judgement of what is good and what is not. Ensure that you fully understand what you copy and that there are no vulnerabilities in it. 

Build a mental library of where (and not how) you have solved a similar issue.

### Analysis & Design
> First, solve the problem. Then, write the code.
>> John Johnson

You must be able to solve the problem before you tell a computer to solve it. And the best way to check if you can solve the problem is model the problem using classic style of pen and paper. Once you are confident that the model works, then take a pause and get it reviewed by someone. Try to see if some person can understand your solution (model), because if a human cannot understand it chances are very less that a machine can. That's where reviews form such an important role in application development.
Only after the solution is very clear between you and your peers start writing code.

_Sometimes things will not be clear during design time and you just have to start writing code to make things clear. That is perfectly ok. It is just that you are writing code to prove something or make somethings clear. But this should happen by design_

### Beware of Fortunate happenstance
The strategy of _**Just write code and it will get done**_ doesn't work in real world. Just like in a game of chess you play and suddenly discover that you have given yourself the opportunity for checkmate in two moves. This is not how programming should be done. 
All software will have bugs there is no denying that. As a programmer I need to reduce the bugs to acceptable levels. A  programmer will have to ensure that there are no obvious bugs. You can protect in multiple ways
+ Testing is the first thing, main thing and the only thing. Start from Unit tests and move up to Integration tests. There is no such thing to say how many lines of test are enough, but one measure I use is that, if you change some code and your existing tests don't fail then there is something to have second look at.
+ Always use Continous Integration and Continous Deployment. First thing you do before you start any development activity is to setup a CI/CD environment. No coding should begin without CI.
+ Get it reviewed, may be more than once. Important thing about review is, it should be done in small batches. No reviewer will like to review hundreds of file. Try to create small pull requests (comprising changes to not more than 5-6 files)
+ Ensure it runs on all environments. It is very common to hear that code works on my machine. With advance in technologies like containerization and virtualization it should be very easy to test if your code works on any and every environment

### Perseverance
Never give up. Always believe you can accomplish any programming task. Be pragmatic and take stock of things. If things are not going your way, be brave to take some tough decisions which might include re-writing from scratch.

### Requirements
I have seen many programmers with the mindset waiting to write code  till you get a complete, accurate and concrete list of the system requirements up-front. Let me tell you in my experience I have never seen a concrete set of system requirements. 

As a programmer you should be able to start your role with minimalistic set of requirements with full knowledge that they would change in future. Having this thread running in back of your mind will make you question yourself at every stage of development. Keep asking yourself
+ _"What will happen to my code if this requirement changes?"_
+ _"Can I extend my code to incorporate new features if added in future?_
+ _"What will be the impact on other systems if my code change?_

### Continuous learning
A career in programming is like running on a treadmill. Just as on a moving treadmill you try to stop you will fall, so in this field of technology if you stop to learn new things you will fall off. Learning is essential to career of a programmer. If we want to survive we constantly need to learn how to get better, every single day

## Conclusion
I am not condoning mediocrity; you should always do your best in whatever you do — including programming. What I want to say is you don't have to be a whiz-bang programmer to be a great developer. The elitist belief that only superb programmers can write "sophisticated code" is not true. I have followed the practises above and I am sure by doing the same every programmer can be capable of producing high-quality code.