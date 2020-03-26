package com.tutorials.scala.advanced
object StreamsExamples extends App {

  //Lists are eager collections -  they evaluate entries eagerly
  val aList = 1 :: 2 :: 3 ::4 :: 5 :: Nil //List(1, 2, 3, 4 ,5)

  //Streams are Lazy collections -  they evaluate entries lazily
  val aStream = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Stream.empty  //Stream(1, 2, 3, 4 ,5)

  println(aList)
  println(aStream)

  val ints = ( 1 to 100000000).toStream

  println(ints)
  println(ints.head)
  println(ints.tail)
  println(ints.take(100))

  //not safe
  //println(ints.max)
  //println(ints.min)

//  val result = Stream {
//    1/0
//  }
//
//  println(result)

  println(ints.take(100).print())

  //LazyList
  //Stream - head is eagerly but tail is lazily evaluated
  //LazyList - both head and tail are lazily evaluated
  //LazyList(1, 2, 3, ...)



}
