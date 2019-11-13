package com.tutorials.scala.advanced

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class ShoppingCartSpec extends FlatSpec with Matchers with BeforeAndAfterEach {
  var shoppingCart :ShoppingCart = _

  override def beforeEach() = {
    shoppingCart = new ShoppingCart()
  }

  "The ShoppingCart" should
    "add items as expected" in {
    shoppingCart.add(Item("apple", 1.0))

    shoppingCart.getCount shouldBe 1
    shoppingCart.getItems should contain(Item("apple", 1.0))
  }

  it should "delete items as expected" in {
    shoppingCart.add(Item("apple", 1.0))
    shoppingCart.getCount shouldBe 1
    shoppingCart.getItems should contain(Item("apple", 1.0))

    shoppingCart.delete(Item("apple", 1.0))
    shoppingCart.getCount shouldBe 0
    shoppingCart.getItems should not contain Item("apple", 1.0)

  }

  it should "return 0.0 for total when there are no items in the cart" in {
    //shoppingCart.add(Item("apple", 1.0))
    shoppingCart.getTotal shouldBe 0.0
  }

  it should "compute total correctly" in {
    shoppingCart.add(Item("apple", 1.0))

    shoppingCart.getTotal shouldBe 1.0
  }

  it should "compute total correctly where cart contains more than one item" in {
    shoppingCart.add(Item("apple", 1.0))
    shoppingCart.add(Item("orange", 2.0))
    shoppingCart.add(Item("banana", 3.5))

    shoppingCart.getTotal shouldBe 6.5
  }



   def add(x: Int, y: Int) = {
    x + y
  }

}
