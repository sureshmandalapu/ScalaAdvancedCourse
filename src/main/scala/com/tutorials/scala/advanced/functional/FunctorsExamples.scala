package com.tutorials.scala.advanced.functional

import scala.concurrent.Future

object FunctorsExamples extends App {

  // A functor is anything that provides a "map" function and satisfies the following two laws
  // 1. identity:
  // Mapping with the identity function is a no-op
  // fa.map(x => x) = fa

  // 2. Composition:
  // Mapping with f and then again with g is the same as mapping once with the composition of f and g
  // fa.map(f).map(g) = fa.map(f.andThen(g))

  //  Option(1)
  //  List(1, 2)
  //  Future(2)
  //  Right(2)

  //println(List(1, 2, 3).map(x => x))
  //println(Some(1).map(x => x))
  // println(None.map(x => x))

  def f: Int => Int = x => x + 1

  def g: Int => Int = x => x + 2

  val r1 = List(1, 2, 3).map(f).map(g)
  val r2 = List(1, 2, 3).map(f.andThen(g))

  //println(r1)
  //println(r2)

  //Define a Functor
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]

    // map[Int, Int](List(1, 2, 3))(x => x + 1) : List[2, 3, 4]
  }

  val listFunctor = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = {
      val result = for {
        x <- fa
      } yield f(x)
      result
    }
  }

  val r3 = listFunctor.map[Int, Int](List(1, 2, 3))(x => x + 1)

  //println(r3)

  val optionFunctor = new Functor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = {
      fa match {
        case Some(x) => Some(f(x))
        case None => None
      }
    }
  }

  //Futures are also functors because they provide map function

  //println(optionFunctor.map[Int, Int](Option(1))(x => x + 1))


  def ff(a: Int, b: Int) = a + b

  val x = (ff _).curried

  //println(x)

  //println(List(1, 2, 3).map(x => x + 1))

  //println(List(1, 2, 3).map(x))

  val add = (x: Int, y: Int) => x + y
  val inc = add.curried

  val r: List[Int => Int] = listFunctor.map(List(1, 2, 3))(add.curried)

  //println(r.map(x => x(1)))

  trait Apply[F[_]] extends Functor[F] {
    def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

    //def ap[Int, Int](List(x => x + 1)(List(1, 2, 3)): List(2, 3, 4)
  }

  val listApply = new Apply[List] {
    override def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] = {
      val xx = ff.flatMap(x => fa.map(x))
      xx
    }

    override def map[A, B](fa: List[A])(f: A => B): List[B] = for {
      x <- fa
    } yield f(x)
  }

  trait Applicative[F[_]] extends Apply[F] {
    def pure[A](x: A): F[A]

    //def map[A, B](fa: F[A])(f: A => B): F[B]
    //def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

    def map[A, B](fa: F[A])(f: A => B): F[B] = ap(pure(f))(fa)
  }

  val listIntApplicative = new Applicative[List] {
    override def pure[A](x: A): List[A] = List(x)

    def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] =
      ff.flatMap(x => fa.map(x))
  }

  //println( "xx " + listIntApplicative.map(List(1, 2, 3))(x => x + 11))


  def add(x: Int, y: Int) = x + y

  add.curried

  //println(listFunctor.map(List(1, 2,3))(add.curried))
  val list1 = List(1, 2, 3)
  val list2 = List(4, 5, 6)
  val x1 = listIntApplicative.ap(List(add.curried))(list1)
  x1

  val x2 = listIntApplicative.ap(x1)(list2)

  println(x2)

  //println(listIntApplicative.p)


  //println(list1.flatMap(x => list2.map(y => x + y)))

  val OptionIntApplicative = new Applicative[Option] {
    override def pure[A](x: A): Option[A] = Option(x)

    def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] = {
      val r = fa match {
        case None => None
        case Some(x) => ff.map(k => k(x))
      }

      r
      //ff.flatMap(x => fa.map(x))
    }
  }

  def ad(x: Int)(y: Int) = x + y

  val o1 = Option(1)
  val o2 = Option(2)

  val j = OptionIntApplicative.ap(Option(ad _))(o1)

  val jj = OptionIntApplicative.ap(j)(o2)

  println(jj)

}
