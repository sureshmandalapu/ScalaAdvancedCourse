package com.tutorials.scala.advanced

import play.api.libs.json.Json
case class Test(name: String, age: Int)


object Test {

  val format = Json.format[Test]

  Json.parse("")

}