package com.tutorials.scala.advanced.functional


object ApplicativeExamples extends App {

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait Applicative[F[_]] extends Functor[F] {
    //If we view Functor as the ability to work with a single effect,
    // Applicative encodes working with multiple independent effects.
    // Between product and map, we can take two separate effectful values and compose them.
    // From there we can generalize to working with any N number of independent effects.
    def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]

    def pure[A](a: A): F[A] //lift operation
    //pure wraps the value into the type constructor -
    //for Option this could be Some(_), for Future Future.successful, and
    //for List a singleton list...etc
  }

  class OptionApplicative extends Applicative[Option[Int]] {
    override def product[A, B](fa: Option[A], fb: Option[B]): Option[(A, B)] =

      (fa, fb) match {
        case (Some(a), Some(b)) => Some((a, b))
        case (None, Some(b)) => None
        case (Some(_), None) => None
        case _ => None
      }

    override def pure[A](a: A): Option[A] = Option(a)

    override def map[A, B](fa: Option[A])(f: A => B): Option[B] =
      fa.map(f)
  }

}
