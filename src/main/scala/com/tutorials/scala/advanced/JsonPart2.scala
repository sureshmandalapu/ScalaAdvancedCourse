package com.tutorials.scala.advanced

import play.api.libs.json.{Format, JsError, JsResult, JsSuccess, Json, Reads, Writes}

object JsonPart2 extends App {

  val emp = Employee("Suresh", 30, Some("Male"), Address("line1", None, None, "some-postcode", "GB"))

  //Serialization a class into a json we need to supply implicit writes

  val result = Json.toJson(emp)

  println(result)

  //Deserialisation into a class

  val emp2 = result.as[Employee]

  println(emp2)


  val person = Person("Suresh", Right("Manager"))

  println(Json.toJson(person))

  val personJson = Json.toJson(person)

  println(personJson.as[Person])

  val personJson2 = """{"name":"Suresh","grade":"r-Manager"}"""
  //println(Json.parse(personJson2).as[Person])

  //Validation
  val r: JsResult[Person] = Json.parse(personJson2).validate[Person]

  r match {
    case JsSuccess(value, path) => println(value)
    case JsError(errors) => println(errors)
  }


  case class Employee1(name: String, age: Int, isAdult: Boolean)
  implicit val format2: Format[Employee1] = Json.format[Employee1]

  case class Employees(employees: List[Employee1])
  implicit val format1: Format[Employees] = Json.format[Employees]

  val x = Json.toJson(Employees(List(Employee1("David", 10, false), Employee1("John", 30, true))))
  println(x)
}
