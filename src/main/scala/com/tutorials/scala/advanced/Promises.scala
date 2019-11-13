package com.tutorials.scala.advanced

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
object Promises extends App {

  //Futures are read-only
  val myF: Future[Int] = Future {
    //
    1
  }

  myF.map {
    x =>  x
  }

  //Promises - are writable and set the value of a future

  val myP: Promise[Int] = Promise()

  myP.success(22)
 // myP.failure(new RuntimeException())

  val res = myP.future

  println(Await.result(res, 2 seconds))








}
