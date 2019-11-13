package com.tutorials.scala.advanced

import play.api.libs.json.{Format, JsError, JsResult, JsSuccess, JsValue, Json, Reads, Writes}

case class Address(line1: String, line2: Option[String], line3: Option[String], postcode: String, countryCode: String )

object Address {
  implicit val addFormat: Format[Address] = Json.format[Address]
}


case class Employee(name: String, age: Int, gender: Option[String] = None, address: Address)

object Employee {
  //implicit val empWrites: Writes[Employee] = Json.writes[Employee]
 // implicit val empReads: Reads[Employee] = Json.reads[Employee]
  implicit val empFormat: Format[Employee] = Json.format[Employee]
}

case class Person(name: String, grade: Either[String, String])

object Person {
  implicit val personFormat: Format[Person] = new Format[Person] {
    override def writes(person: Person): JsValue = {
      val gradeText = person.grade match {
        case Right(g) =>  "r-" + g
        case Left(g) =>  "l-" + g
      }

      Json.obj("name" -> person.name, "grade" -> gradeText)
    }

    override def reads(json: JsValue): JsResult[Person] = {
      val name = (json \ "name").as[String]

      val gradeText = (json \ "grade").asOpt[String]
      gradeText match {
        case Some(text) =>
          val grade =  if(text.startsWith("r-")) {
            Right(text.stripPrefix("r-"))
          } else {
            Left(text.stripPrefix("l-"))
          }

          JsSuccess(Person(name, grade))

        case None => JsError("grade attribute is not found")
      }

    }
  }
}


