package com.tutorials.scala.advanced.functional

object MonoidsExamples extends App {

  //identify law

  1 + 0 == 0 + 1 == 1 //a + 0 == 0 + a == a  for any integer a //identity law

  "suresh" + "" == "" ++ "suresh" == "suresh"

  List(1, 2) ++ List.empty[Int] == List.empty[Int] ++ List(1, 2)

  trait SemiGroup[A] {
    def combine(a: A, b: A): A //combine method should be associative
  }

  trait Monoid[A] extends SemiGroup[A] {
    //fallback
    def empty: A //this should satisfy identity law
    //combine(x, empty) = combine(empty, x) = x
  }

  val intMonoid = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(a: Int, b: Int): Int = a + b
  }

  val stringMonoid = new Monoid[String] {
    override def empty: String = ""

    override def combine(a: String, b: String): String = a + b
  }

  val listMonoid = new Monoid[List[Int]] {
    override def empty: List[Int] = List.empty[Int]

    override def combine(a: List[Int], b: List[Int]): List[Int] = a ++ b
  }

  //Advantages of Monoids
  //useful when merging collections using fold, foldLeft, foldRight

  val ints = List[Int](1, 2, 3, 4, 5)

  ints.fold(0)((a, b) => a + b )

  ints.fold(intMonoid.empty)(intMonoid.combine)


  //MoneyMonoid examples
  case class Money(dollars: Int, cents: Int)

  val moneyMonoid = new Monoid[Money] {
    override def empty: Money = Money(0, 0)

    override def combine(x: Money, y: Money): Money = {
      Money(x.dollars + y.dollars + ((x.cents + y.cents) / 100),
        (x.cents + y.cents) % 100)
    }
  }

  val totalExpenses = List(Money(3, 4), Money(34, 5), Money(12, 0))

  def totalAllExpenses(expenses: List[Money]): Money = {
    expenses.foldLeft(moneyMonoid.empty){
      case (acc, money) => moneyMonoid.combine(acc, money)
    }
  }

  println(s"totalExpenses : ${totalAllExpenses(totalExpenses)}")

  val optionMonoid = new Monoid[Option[Int]] {
    override def empty: Option[Int] = None

    override def combine(a: Option[Int], b: Option[Int]): Option[Int] =
      a match {
        case None => b
        case Some(xv) => b match {
          case None => a
          case Some(yv) => Some(xv + yv)
        }
      }
  }
}
