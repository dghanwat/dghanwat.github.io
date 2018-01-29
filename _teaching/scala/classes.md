---
title: "OOPS in Scala"
collection: Training
type: "chapter"
permalink: /teaching/scala/classes
venue: "Online"
date: 2016-02-23
location: "Pune, India"

---
# OOPS in Scala
---
## Classes
*   Classes in Scala are static templates that can be instantiated into many objects at runtime
*   A Class can contain information about:
    *   Fields
    *   Constructors
    *   Methods
    *   Superclasses (inheritance)
    *   Interfaces implemented by the class, etc.
```scala
// Simple Class
class MyClass {}
```
---
*   Class in Scala is very much similar to Java or C++
*   Class contains fields and methods
```scala
class Emp {
    private var employeeNumber = 0
    var employeeName =null
    def display(empNumber: String, empName: String) = {
        println(s"Employee Number is ${empNumber}")
        println(s"Employee Name is ${empName}")
    }
}
```
*   In Scala a class is NOT declared as public.
*   A source file can contain multiple  classes.
*   All of the classes could be public
---
Classes can be used as
```scala
    val emp = new Emp()
    emp.display("8597","Dhananjay")
```
*   Parameter less method could be called with or without parentheses
*   Using any form is programmer’s choice
*   However, as convention
    *   Use () for mutator method
    *   Use no parentheses for accessor method
---
Check your Understanding
*   What is the output of the following code ?
```scala
class add {
  var x:Int=10
  var y:Int=20
  def add(a:Int,b:Int)  {
    a = x + 1
    println(s"Value of a after modification :${+a}");

  }
}
var p =new add()  
p.add(5,10)
```
---
*   In Scala, the getters and setters are generated for each property
*   For private properties, the getter and setter are private
*   For a val, only getters are generated
*   In Scala you can’t have a read-only property (i.e. only getter, no setter)
*   No getters and setters are generated for object-private fields
---
## Check your Understanding
Which of the following statements are correct about getter() with properties?

*   The property value is changed indirectly
*   The property value is changed directly
*   The property value never changes
*   Option a and c
---
## Constructors
*   Constructors in Scala are a bit different than in Java
*   Scala has 2 types of constructors
    *   Primary Constructors
    *   Auxiliary Constructors
---
## Auxiliary Constructors
*   The auxiliary constructors in Scala are called this. This is different from other languages, where constructors have
the same name as the class

*   Each auxiliary constructor must start with a call to either a previously defined auxiliary constructor or the primary  constructor
---
```scala
class Person {
  var firstName: String = _
  var lastName: String = _

  def this(firstName: String){
    this()
    this.firstName = firstName
  }

  def this(firstName: String, lastName: String) {
    this(firstName)
    this.lastName = lastName
  }
}
```
---
## Primary Constructor
*   Every class in Scala has a primary constructor
*   Primary constructor isn’t defined by this method
*   The parameters for primary constructor are placed immediately after the class name:
```scala
class Greet(message: String ) {
// … code
}
```
---
## Check your Understanding
*   Syntax of primary constructor is:
```scala
a.	Greet(message : String) { // …code }
```
```scala
b.	class Greet{ // …code }
```
```scala
c.	class Greet(message : String) { // …code }
```
```scala
d.	public Greet(message : String) { // …code }
```








