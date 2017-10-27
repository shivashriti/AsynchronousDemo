package unittest

import java.util.concurrent.TimeUnit
import controllers.ApplicationController
import akka.actor.ActorSystem
import akka.util.Timeout
import org.scalatestplus.play._
import play.api.test.FakeRequest
import play.api._
import play.api.libs.json.JsString
import play.api.test.Helpers._
import play.libs.Json

/**
  * Created by Shiva on 20/04/2017.
  */

class
ApplicationControllerSpec extends PlaySpec with OneAppPerSuite{
  implicit val timeout = Timeout(10, TimeUnit.SECONDS)
  val testActorSystem = ActorSystem("testsystem")
  val applicationController = new ApplicationController(testActorSystem)
  "AsynchronousDemo Application" must {

    "Test getEmployee - success case" in {
      val request = FakeRequest(GET, "/api/employees/A1030")
      val response = applicationController.getEmployees("A1030")(request)
      Logger.info("Status =>" + play.api.test.Helpers.status(response)(timeout))
      status(response)(timeout) mustBe OK
    }

    "Test getEmployee - fail case" in {
      val request = FakeRequest(GET, "/api/employees/A1010")
      val response = applicationController.getEmployees("A1010")(request)
      Logger.info("Status =>" + play.api.test.Helpers.status(response)(timeout))
      status(response)(timeout) mustBe NOT_FOUND
    }

    "Test deleteEmployee - success Case" in {
      val request = FakeRequest(DELETE, "/api/employees/A1020")
      val response = applicationController.deleteEmployee("A1020")(request)
      Logger.info("Status =>" + play.api.test.Helpers.status(response)(timeout))
      status(response)(timeout) mustBe OK
    }
  }
}
