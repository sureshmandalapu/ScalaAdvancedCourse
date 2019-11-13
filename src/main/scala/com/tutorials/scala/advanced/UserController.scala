package com.tutorials.scala.advanced

import com.tutorials.scala.advanced.FriendsService.Friend
import com.tutorials.scala.advanced.TweetsService.Tweet
import com.tutorials.scala.advanced.UserController.{User, UserInfo}

import scala.concurrent.{ExecutionContext, Future}

class UserController(friendsService: FriendsService, tweetsService: TweetsService) {
  def showHomePage(user: User)(implicit ec: ExecutionContext): Future[UserInfo] = {
    val friends = friendsService.getFriends(user.id)
    val tweets = tweetsService.getTweets(user.id)

    val result = friends.flatMap {
      fs =>
        tweets.map {
          ts =>
            UserInfo(fs, ts)
        }
    }
    result
  }
}

object UserController {
  case class User(id: Int, name: String)
  case class UserInfo(friends: List[Friend], tweets: List[Tweet])
}
