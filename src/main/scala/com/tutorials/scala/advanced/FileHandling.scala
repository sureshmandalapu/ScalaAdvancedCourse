package com.tutorials.scala.advanced

import java.io.{File, FileNotFoundException, PrintWriter}

import scala.io.Source
import scala.util.{Failure, Success, Try}

object FileHandling extends App {

  //Reading from files
  //getClass.getResourceAsStream("movie_ratings.csv")

  val data = Source.fromResource("movie_ratings.csv")
    .getLines()
    .toList
      .tail

  data.map(println)

  //Using fromFile

  val result = Try {
    val path = "/Users/tutorials/IdeaProjects/ScalaAdvancedCourse/src/main/resources/movie_ratings.csv"
    val file = Source.fromFile(path)
    val data2 = file.getLines().toList
    //println(data2)
    file.close()
    data2
  } match {
    case Success(value) => Right(value)
    case Failure(exception: FileNotFoundException) => Left(exception.getMessage)
    case Failure(exception) => Left(exception.getMessage)
  }

  println(result)

  //Creating files
  val file = new File("/Users/tutorials/IdeaProjects/ScalaAdvancedCourse/src/main/resources/movie_ratings_new.csv")

  val pw = new PrintWriter(file)
  pw.println(1111)
  pw.close()
}
