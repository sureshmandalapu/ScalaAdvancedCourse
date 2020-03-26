package com.tutorials.scala.advanced.functional

object SemiGroupExamples extends App {

  //What is a Semigroup ?

  1 + 2 == 2 + 1 // a + b == b + a

  (1 + 2) + 3 == 1 + (2 + 3) // (a + b) + c == a + (b + c) // associative operation


  (1 * 2) * 3 == 1 * (2 * 3) // (a * b) * c == a * (b * c) //associative operation

  (1 - 2) - 3 != 1 - (2 - 3) //Not associative

  //Union of Sets

  //(A U B) U C == A U (B  U C) //associative operation

  //A Semigroup can be anything that supports associative operation

  // "combine"

  trait SemiGroup[A] {
    def combine(a: A, b: A): A //combine method should be associative
    //combine(x, combine(y, z)) = combine(combine(x, y), z)
    //such combine examples are int addition, set union... etc
  }

  val intSemigroup = new SemiGroup[Int] {
    override def combine(a: Int, b: Int): Int = a + b
  }

  val stringSemigroup = new SemiGroup[String] {
    override def combine(a: String, b: String): String = a + b
  }

  //Why Semigroups ?

  //Semigroups allow us to parallelize operations on large and small data sets,
  // then combine the results together.
  // In essence Semigroup encapsulates the reduce part of map reduce.

  val group1 = intSemigroup.combine(1, 2)
  val group2 = intSemigroup.combine(3, 4)
  val group3 = intSemigroup.combine(group1, group2)
  val total1 = intSemigroup.combine(group3, 5)

  println(total1)

  val group11 = intSemigroup.combine(2, 3)
  val group22 = intSemigroup.combine(4, 5)
  val group32 = intSemigroup.combine(group11, group22)
  val total2 = intSemigroup.combine(1, group32)

  println(total2)

  //In functional programming terms a Semigroup is a concept
  // which encapsulates this combining/aggregating process

  val optionSemigroup = new SemiGroup[Option[Int]] {
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
