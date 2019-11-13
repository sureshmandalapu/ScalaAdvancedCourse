package com.tutorials.scala.advanced

import scala.collection.mutable.ListBuffer

case class Item(name: String, unitPrice: Double)

class ShoppingCart {

  private val items = ListBuffer.empty[Item]

  def add(item: Item): ListBuffer[Item] = {
    //if using scala version is 2.13, then you can also use items.addOne(item)
    items.+=(item)
  }

  def delete(item: Item): ListBuffer[Item] = {
    items -= item //items = items - item
  }

  def getTotal: Double = {
    items.foldLeft(0.0)((accSum, item ) => accSum + item.unitPrice)
  }

  //just for unit testing
  def getCount: Int = items.size

  //just for unit testing
  def getItems: List[Item] = items.toList
}
