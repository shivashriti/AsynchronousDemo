package controllers

import java.util.concurrent.TimeUnit
import javax.inject._

import utils.JsonResponse._
import actors.ServiceActor
import actors.ServiceActor._
import akka.actor.ActorSystem
import akka.util.Timeout
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.duration._
import akka.pattern.ask
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import play.api.Logger._

import scala.concurrent.Future

/**
  * Created by Shiva on 20/04/2017.
  */

/*This is the main controller. It will call service actor for all requests
 after basic validations on request if required*/

@Singleton
class ApplicationController @Inject()(system: ActorSystem) extends Controller {

  val serviceActor = system.actorOf(ServiceActor.props, "service-actor")
  implicit val timeout = Timeout(10, TimeUnit.SECONDS)

  def getEmployees(empId: String) = Action.async { implicit request =>
    Logger.info("ApplicationController: getEmployees")
    (serviceActor ? GetEmployeeRequest(empId)).mapTo[GetEmployeeResponse]
      .map{ empResponse =>
        Logger.info(s"getEmployee response: $empResponse")
        empResponse.empDetails match {
          case Some(emp) => Ok(Json.toJson(emp))
          case None => NotFound("not found")
        }
      }
  }

  def addEmployee = Action.async(parse.json) { implicit request =>
    Logger.info("ApplicationController: addEmployee")
    request.body.validate[Employee].fold (
      error => Future.successful(BadRequest(error.mkString(""))),
      employee =>
      (serviceActor ? AddEmployeeRequest(employee)).mapTo[AddEmployeeResponse]
        .map { empResponse =>
          Logger.info(s"addEmployee response: $empResponse")
          empResponse.status match {
            case Some(CREATED) => Created(s"${employee.empId} added successfully")
            case Some(PRECONDITION_FAILED) => PreconditionFailed("Invalid data. empid must start with A10")
            case None => InternalServerError
            case _ => BadRequest
          }
        }
    )
  }

  def deleteEmployee(empId: String) = Action.async { implicit request =>
    Logger.info("ApplicationController: deleteEmployee")
    (serviceActor ? DeleteEmployeeRequest(empId)).mapTo[DeleteEmployeeResponse]
      .map{ empResponse =>
        Logger.info(s"deleteEmployee response: $empResponse")
        empResponse.status match {
          case Some(OK) => Ok(s"employee $empId deleted successfully")
          case Some(NOT_FOUND) => NotFound("not found")
          case None => InternalServerError
          case _ => BadRequest
        }
      }
  }
}
