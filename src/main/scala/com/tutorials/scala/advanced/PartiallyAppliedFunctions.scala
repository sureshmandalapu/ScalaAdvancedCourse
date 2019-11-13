package com.tutorials.scala.advanced

object PartiallyAppliedFunctions {
  def main(args: Array[String]): Unit = {

    def computeTotalPrice(vat: Double, serviceCharge: Double, itemPrice: Double): Double = {
      itemPrice + itemPrice * serviceCharge /100 + itemPrice * vat / 100
    }

    val totalPrice = computeTotalPrice(20, 5, 50)

    val computeTotalPricePa = computeTotalPrice(20, 5, _: Double)

    val totalPricePa = computeTotalPricePa(50)

    println(totalPrice)

    println(totalPricePa)

    //passing fewer no. of parameters to a function


    def itemTotal(itemPrice: Double)(vat: Double) = {
      //some impl
      itemPrice + itemPrice * vat / 100
    }

    def importCharge(charge: Double)(vat: Double) = {
      //some impl
      charge + charge * vat / 100
    }

    def someOtherCharge(other: Double)(vat: Double) = {
      //some impl
      other + other * vat / 100
    }

    val it = itemTotal(50)(20)
    val ic = importCharge(30)(20)
    val soc = someOtherCharge(10)(20)
    val grandTotal = it + ic + soc
    println(grandTotal)
  }
}
