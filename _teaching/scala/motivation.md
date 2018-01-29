---
title: "Scala: Motivation"
collection: Training
type: "chapter"
permalink: /teaching/scala/motivation
venue: "Online"
date: 2016-02-23
location: "Pune, India"
---


<!-- .slide: data-background="./images/motivation-bg.png" -->
# Why Scala
> If I were to pick a language to use today other than Java, it would be Scala.

_**James Gosling**_
---
<!-- .slide: data-background="./images/motivation-bg.png" -->
# Why Scala
> Scala, it must be stated, is the current heir apparent to the Java throne. No other language on the JVM seems as capable of being a "replacement for Java" as Scala, and the momentum behind Scala is now unquestionable.

_**Charlies Olivier Nutter - JRuby lead**_
---
<!-- .slide: data-background="./images/learning-bg.png" -->

---
<!-- .slide: data-background="./images/trans-from-java-scala-bg.png" -->

---
## Java
```java
public class Person {
  private int age;
  private String name;

  public Person(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
```
## Scala
```java
class Person(var age: Int, var name: String)
```

---
## Java
```java
List<Person> persons = ...
List<Person> adults = new LinkedList<Person>(); List<Person> kids = new LinkedList<Person>();
for (Person person : persons) {
  if (person.getAge() < 18) {
    kids.add(person);
  } else {
    adults.add(person);
  }
}
```
## Scala

```java
val (kids, adults) = persons.partition(_.age < 18)
```
---
# Scala
* Object oriented and functional
* Statically typed
* Java compatible
    * Complies to Java bytecode (and CLR)
    * Existing libraries/frameworks
* Better Java
---
<!-- .slide: data-background="./images/why-scala-bg.png" -->
---
