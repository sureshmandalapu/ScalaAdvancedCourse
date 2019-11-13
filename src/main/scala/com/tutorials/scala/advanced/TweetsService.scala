package com.tutorials.scala.advanced

import com.tutorials.scala.advanced.TweetsService.Tweet

import scala.concurrent.{ExecutionContext, Future}

class TweetsService {

  def getTweets(userId: Int)(implicit ec: ExecutionContext): Future[List[Tweet]] = {

    //a time consuming task to retrieve all friends from Database or from other service
    //DO NOT USE Thread.sleep() in real time projects
    Future {
      Thread.sleep(100)
      List(Tweet("msg1"), Tweet("msg2"), Tweet("msg3"))
    }
  }

}

object TweetsService {
  case class Tweet(message: String)
}
