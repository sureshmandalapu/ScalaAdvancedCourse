package com.tutorials.scala.advanced

import play.api.libs.json.{JsNumber, JsString, JsValue, Json, Reads}

object JsonExamples extends App {

  val employeeStr1 = """{"name": "Suresh", "age": 30}"""

  val employeeStr2 =
    """
      | {
      |  "name" : "Suresh",
      |  "age" : 30,
      |  "address" : {
      |   "line1": "some-line1",
      |   "line2": "some-line2",
      |   "line3": "some-line3",
      |   "line4": "some-line4",
      |   "postcode": "abcd",
      |   "country": "UK"
      |  }
      |}
    """.stripMargin

  val employeeJson = Json.parse(employeeStr2)

  println(employeeJson)

  //Reading attributes/elements out a json object

  val age = (employeeJson \ "age").as[Int]

  println(age)

  val name = (employeeJson \ "name").as[String]

  println(name)

  val address = (employeeJson \ "address").as[JsValue]

  println(address)

  val line2 = (employeeJson \ "address" \ "line2").asOpt[String]

  println(line2)

  //Writing to a json or creating a json

  val empJson1 = Json.obj(
    "name" -> "Suresh",
    "age" -> 30,
    "address" -> Json.obj(
      "line1" -> "some-line1",
      "line2" -> "some-line2",
      "line3" -> "some-line3",
      "postcode" -> "abcd",
      "country" -> "UK"
    )
  )

  println(empJson1)

   case class EmployeeStr1(name: String, age: Int)

  implicit val reads: Reads[EmployeeStr1] = Json.reads[EmployeeStr1]

  println(Json.parse(employeeStr1).as[EmployeeStr1])

}
