package com.tutorials.scala.advanced.functional

import scala.concurrent.Future

object MonadExamples extends App {

  //Monad Laws --> Left identity, Right identity and Associativity
  trait Monad[F[_]] {
    //a way to create a new monadic context from a plain value.
    def pure[A](a: A): F[A]

    //flattens a nested context F[F[_]] and return F[_]
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  }

  class OptionMonad extends Monad[Option] {
    override def pure[A](a: A): Option[A] = Some(a)

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
      fa.flatMap(f)
  }

  class  ListMonad extends Monad[List] {
    override def pure[A](a: A): List[A] = List(a)

    override def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] =
      fa.flatMap(f)
  }

  class FutureMonad extends Monad[Future] {
    override def pure[A](a: A): Future[A] = ???

    override def flatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] = ???
  }
}
