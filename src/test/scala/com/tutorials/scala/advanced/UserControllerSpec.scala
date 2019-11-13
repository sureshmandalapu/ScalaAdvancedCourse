package com.tutorials.scala.advanced

import com.tutorials.scala.advanced.FriendsService.Friend
import com.tutorials.scala.advanced.UserController.{User, UserInfo}
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers, WordSpec}

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
class UserControllerSpec extends WordSpec with Matchers with BeforeAndAfterEach {

  val controller = new UserController(new FriendsService(), new TweetsService())

  "The UserController" when {
    "showHomePage is called it" should {
      "return the UserInfo as expected" in {
        val result = controller.showHomePage(User(1, "suresh"))

        val userInfo = Await.result(result, 2 seconds)

        userInfo.friends shouldBe List(Friend("Suresh"), Friend("Daniel"))
      }

      "blah blah" in {
        //1 should
      }
    }
  }

}
