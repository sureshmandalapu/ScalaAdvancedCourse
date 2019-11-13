package com.tutorials.scala.advanced

import com.tutorials.scala.advanced.FriendsService.Friend

import scala.concurrent.{ExecutionContext, Future}
//import scala.concurrent.ExecutionContext.Implicits.global

//An ExecutionContext executes a task it’s given.
// You can think of it as being like a thread pool.


//1. Use Futures to handle with long, time consuming tasks and to avoid any blocking calls
//2. ExecutionContext - executes the Future task in an asynchronous way in a different threads,

class FriendsService {

  def getFriends(userId: Int)(implicit ec: ExecutionContext): Future[List[Friend]] = {

    Future {
      //a time consuming task to retreive all friends from Database or from other service
      //DO NOT USE Thread.sleep() in real time projects
      Thread.sleep(100)
      List(Friend("Suresh"), Friend("Daniel"))
    }
  }
}

object FriendsService {
  case class Friend(name: String)
}
