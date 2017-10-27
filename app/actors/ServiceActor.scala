package actors

import java.util.concurrent.TimeUnit
import utils.JsonResponse._
import utils.AppDAOImpl
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import akka.pattern.ask
import akka.util.Timeout
import play.api.Logger
import play.api.http.Status._
import scala.concurrent._
import ExecutionContext.Implicits.global

/**
  * Created by Shiva on 20/04/2017.
  */

/*This is the main actor for service layer. It must receive all requests from controller
* and perform business/validatoin logic. Service actor then forwards/ breaks the request and sends to one or more worker actors
* Receives their individual responses and based on them forms the final Response to be sent to controller.*/

object ServiceActor{
  def props = Props[ServiceActor]

  case class GetEmployeeRequest(empId: String)
  case class GetEmployeeResponse(empDetails: Option[Employee])
  case class AddEmployeeRequest(employee: Employee)
  case class AddEmployeeResponse(status: Option[Int])
  case class DeleteEmployeeRequest(empId: String)
  case class DeleteEmployeeResponse(status: Option[Int])
}

class ServiceActor extends Actor {
  import ServiceActor._
  implicit val system = context.system
  val workerWorker = system.actorOf(Props(classOf[WorkerActor], AppDAOImpl), "worker-actor")
  implicit val timeout = Timeout(10, TimeUnit.SECONDS)

  def receive: Receive = {
    case getEmployeeRequest: GetEmployeeRequest => getEmployeeDetails(sender(), getEmployeeRequest)
    case addEmployeeRequest: AddEmployeeRequest => addEmployee(sender(), addEmployeeRequest)
    case deleteEmployeeRequest: DeleteEmployeeRequest => deleteEmployeeDetails(sender(), deleteEmployeeRequest)
  }

  def getEmployeeDetails(sender: ActorRef, getEmployeeRequest: GetEmployeeRequest ) = {
    Logger.info("ServiceActor: getEmployeeDetails")
    (workerWorker ? GetEmployeeRequest(getEmployeeRequest.empId)).mapTo[GetEmployeeResponse]
      .map { workerResponse =>
        Logger.info(s"ServiceActor got response: $workerResponse")
        sender ! workerResponse
      }
  }

  def addEmployee(sender: ActorRef, addEmployeeRequest: AddEmployeeRequest ) = {
    Logger.info("ServiceActor: addEmployee")
    val empId = addEmployeeRequest.employee.empId
      // sample validation where empid should start with A10
    if(empId.nonEmpty && empId.startsWith("A10")) {
      (workerWorker ? AddEmployeeRequest(addEmployeeRequest.employee)).mapTo[AddEmployeeResponse]
        .map { workerResponse =>
          Logger.info(s"ServiceActor got response: $workerResponse")
          sender ! workerResponse
        }
    } else sender ! AddEmployeeResponse(Some(PRECONDITION_FAILED))
  }

  def deleteEmployeeDetails(sender: ActorRef, deleteEmployeeRequest: DeleteEmployeeRequest ) = {
    Logger.info("ServiceActor: deleteEmployeeDetails")
    (workerWorker ? DeleteEmployeeRequest(deleteEmployeeRequest.empId)).mapTo[DeleteEmployeeResponse]
      .map { workerResponse =>
        Logger.info(s"ServiceActor got response: $workerResponse")
        sender ! workerResponse
      }
  }
}