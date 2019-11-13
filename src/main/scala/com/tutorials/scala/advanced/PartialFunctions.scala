package com.tutorials.scala.advanced

object PartialFunctions extends App {

  //def squareRoot(x: Double) = Math.sqrt(x)

  def squareRoot: PartialFunction[Double, Double] = {
    case x if x > 0 => Math.sqrt(x)
  }

  println(squareRoot(25))

  //println(squareRoot(-25))

  println(squareRoot.isDefinedAt(25))
  println(squareRoot.isDefinedAt(-25))

  val x = 36
  if (squareRoot.isDefinedAt(x)) {
    squareRoot(x)
  }

  val list: List[Double] = List(4, 16, 25, -9)

  println(list.map(Math.sqrt))


  println(list.collect(squareRoot))


  //OrElse methods
  trait Tweet {
    val t: String
  }

  case class PersonalTweet(t: String) extends Tweet

  case class GovernmentTweet(t: String) extends Tweet

  case class UnKnownTweet(t: String) extends Tweet

  def handlePersonalTweet: PartialFunction[Tweet, String] = {
    case x: PersonalTweet => s"Personal: ${x.t}"
  }

  def handleGovtTweet: PartialFunction[Tweet, String] = {
    case x: GovernmentTweet => s"Govt: ${x.t}"
  }

  def handleOthersTweet: PartialFunction[Tweet, String] = {
    case x: Tweet => s"Unknown: ${x.t}"
  }

  def handleTweet(tweet: Tweet) =
    handlePersonalTweet.orElse(handleGovtTweet).orElse(handleOthersTweet) (tweet)

  println(handleTweet(GovernmentTweet("Raising tax to 50%")))
  println(handleTweet(PersonalTweet("Thanks for raising the taxes")))
  println(handleTweet(UnKnownTweet("not sure ???")))

  //andThen
  def inc1: PartialFunction[Int, Int] = {
    case x: Int =>  x + 1
  }

  def inc2: PartialFunction[Int, Int] = {
    case x: Int =>  x + 2
  }

  def inc(x: Int) =
    (inc1 andThen inc2)(x)

  println(inc(10))
}
