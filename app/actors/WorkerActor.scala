package actors

import java.util.concurrent.TimeUnit
import javax.inject._

import akka.actor.{Actor, ActorRef, Props}
import actors.ServiceActor._
import akka.util.Timeout
import play.api.Logger
import utils.AppDAO

import scala.concurrent._
import ExecutionContext.Implicits.global

/**
  * Created by Shiva on 20/04/2017.
  */

/*This is a worker actor. There can be multiple worker actors in application based on the entity requirements,
* separation logic etc. Worker Actors directly use the services of Application DAOs to perform any operation in database */

object WorkerActor{
  def props = Props[WorkerActor]
}

class WorkerActor @Inject() (appdao: AppDAO) extends Actor {
  import WorkerActor._
  implicit val timeout = Timeout(10, TimeUnit.SECONDS)

  override def receive: Receive = {
    case getEmployeeRequest: GetEmployeeRequest => getEmployee(sender(), getEmployeeRequest)
    case addEmployeeRequest: AddEmployeeRequest => addEmployee(sender(), addEmployeeRequest)
    case deleteEmployeeRequest: DeleteEmployeeRequest => deleteEmployeeDetails(sender(), deleteEmployeeRequest)
  }

  def getEmployee(sender: ActorRef, getEmployeeRequest: GetEmployeeRequest) = {
    Logger.info("WorkerActor: getEmployee")
    if(getEmployeeRequest.empId.nonEmpty){
      appdao.getEmployee(getEmployeeRequest.empId).map{ daoResponse =>
        Logger.info(s"WorkerActor got response: $daoResponse")
        sender ! GetEmployeeResponse(daoResponse)
      }
    } else sender ! GetEmployeeResponse(None)
  }

  def addEmployee(sender: ActorRef, addEmployeeRequest: AddEmployeeRequest ) = {
    Logger.info("WorkerActor: addEmployee")
    appdao.addEmployee(addEmployeeRequest.employee)
      .map { daoresponse =>
        Logger.info(s"WorkerActor got response: $daoresponse")
        sender ! AddEmployeeResponse(daoresponse)
      }
  }

  def deleteEmployeeDetails(sender: ActorRef, deleteEmployeeRequest: DeleteEmployeeRequest ) = {
    Logger.info("WorkerActor: deleteEmployeeDetails")
    appdao.deleteEmployee(deleteEmployeeRequest.empId)
      .map { daoresponse =>
        Logger.info(s"WorkerActor got response: $daoresponse")
        sender ! DeleteEmployeeResponse(daoresponse)
      }
  }
}