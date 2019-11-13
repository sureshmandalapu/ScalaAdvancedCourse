package com.tutorials.scala.advanced

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object FuturesPart3 extends App {

  //1.Composing Futures

  val f1 = Future {
    1
  }

  val f2 = Future {
    2
  }

  val f3 = Future {
    3
  }

  //  val result1 = f1.flatMap {
  //    r1 => f2.map {
  //      r2 =>
  //        r1 + r2
  //    }
  //  }

  val result1 =
    for {
      r1 <- f1
      r2 <- f2
    } yield r1 + r2


  val result = Await.result(result1, 2 seconds)

  println(s"result is $result")

  val result2 =
    for {
      r1 <- f1
      r2 <- f2
      r3 <- f3
    } yield r1 + r2 + r3


  val r3 = Await.result(result2, 2 seconds)

  println(s"result is $r3")


  //2. Callbacks
  //onComplete, onSuccess, onFailure

  f1.onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception)
  }

  f1.foreach {
    r => println(r)
  }

  //3.Error handling using recovery blocks

  val result5 = f1.map {
    r =>
      1 / 0
      s"result is $r"
  }.recover {
    case _: NullPointerException => "npe"
    case _: ArrayIndexOutOfBoundsException => "aiobe"
    case _: ArithmeticException => "ae"
  }

  //def recover[U >: T](pf: PartialFunction[Throwable, U])
  //def recoverWith[U >: T](pf: PartialFunction[Throwable, Future[U]])
  //recoverWith

  val r4 = Await.result(result5, 2 seconds)

  println(s"result is $r4")

  //sleep for some time so JVM wont shutdown before our future completes
  Thread.sleep(10000)

  //Running Futures in parallel ?

//    val result6 =
//      for {
//        r1 <- f1
//        r2 <- f2
//        r3 <- f3
//      } yield  r1 + r2 + r3
//

  //Sequential
  val result6 = f1.flatMap { r1 =>
    f2.flatMap {
      r2 =>
        f3.map {
          r3 =>
            r1 + r2 + r3
        }
    }
  }

  //Parallel computation
  val result7 =
    Future.sequence(List(f1, f3, f3)).map {
      r => r.sum
    }


  //Interview question (running multiple futures in parallel)
  //1.
  val result8 = for {
    r1 <- Future {1}
    r2 <- Future {2}
  } yield  r1 + r2

  //2.
  val myF1 = Future {1}
  val myF2 = Future {2}

  val result88 = for {
    r1 <- myF1
    r2 <- myF1
  } yield  r1 + r2

  //Exercise
  // Find out what these two methods do and how to use them:
  // Future.successful() and Future.failed()


  val p: Promise[Int] = Promise()

  p.success(22)

  val x = p.future

  println(Await.result(x, 2 seconds))




}
