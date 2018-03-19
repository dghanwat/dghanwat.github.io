class MyClass {}

class Emp {
  private var employeeNumber = 0
  var employeeName =null
  def display(empNumber: String, empName: String) = {
    println(s"Employee Number is ${empNumber}")
    println(s"Employee Name is ${empName}")
  }
}

object Main extends App {
  val emp = new Emp()
  emp.display("8597","Dhananjay")

  val g1 = new Greet("Hello World")

}

class add {
  var x:Int=10
  var y:Int=20
  def add(a:Int,b:Int)  {
    //a = x + 1
    println(s"Value of a after modification :${+a}");

  }
}
var p =new add()
p.add(5,10)

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


class Greet(message: String) {
  def greet() = {
    println(message)
  }
}