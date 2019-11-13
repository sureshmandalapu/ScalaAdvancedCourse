package com.tutorials.scala.advanced

object Implicits {

  def main(args: Array[String]): Unit = {

    //1. Implicit parameters
    //the implicit value must resolve to a single value and to avoid clashes,
    implicit val x: Double = 20
    implicit val y: Int = 30

    def itemTotal(itemPrice: Double)(implicit vat: Double) = {
      //some impl
      itemPrice + itemPrice * vat / 100
    }

    def importCharge(charge: Double)(implicit vat: Double) = {
      //some impl
      charge + charge * vat / 100
    }

    def someOtherCharge(other: Double)(implicit vat: Double) = {
      //some impl

      def another(another: Double) = {
        //some impl
        another + another * vat / 100
      }

      another(other)
    }

    val it = itemTotal(50)
    val ic = importCharge(30)
    val soc = someOtherCharge(10)
    val grandTotal = it + ic + soc
    println(grandTotal)

    //You can only use implicit once in a parameter list and all parameters following it will be implicit.
    //def add(implicit x: Int, y: Int)               // x and y are implicit
    //def add(x: Int, implicit y: Int)               // wont compile
    //def add(x: Int)(implicit y: Int)               // only y is implicit
    //def add(implicit x: Int)(y: Int)               // wont compile
    //def add(implicit x: Int)(implicit y: Int)      // wont compile


    //Implicit Conversions

    implicit def doubleToInt(d: Double): Int = d.toInt

    val d: Double = 20.5
    val i: Int = d

    val d2: Double = 30.5
    val i2: Int = d
    println(i)

    //When the compiler finds an expression of the wrong type for the context,
    // it will look for an implicit Function value of a type that will allow it to typecheck.
    // So if an A is required and it finds a B, it will look for an implicit
    // value of type B => A in scope (it also checks some other places like in the B and A companion objects, if they exist).
    // Since defs can be "eta-expanded" into Function objects, an implicit def xyz(arg: B): A will do as well.


    //Implicit classes
    //Implicit classes make it possible to add new functionality to existing 'closed' classes

//    inc("ABC") -> "BCD"
//    dec("BCD") -> "ABC"

    implicit class ExtendedString(s: String) {
      def increment()  = s.map((c: Char) =>  (c + 1).toChar)
      def decrement()  = s.map((c: Char) =>  (c - 1).toChar)
    }

    println("ABC".increment())
    println("ABC".decrement())


  }

}
