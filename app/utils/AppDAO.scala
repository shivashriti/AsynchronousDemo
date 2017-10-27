package utils

import java.util.concurrent.TimeUnit
import javax.inject._
import utils.JsonResponse._
import akka.util.Timeout
import com.datastax.driver.core.querybuilder.QueryBuilder
import play.api.Logger
import play.api.http.Status._
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global

/**
  * Created by Shiva on 20/04/2017.
  */

/*This is Data Access Object layer, which actually deals with database operations.
* We can have multiple DAOs based on entity requirements/ separation logic involved.
* This makes the application modular and clean.*/

trait AppDAO {
  def getEmployee(empId: String): Future[Option[Employee]]
  def addEmployee(employee: Employee): Future[Option[Int]]
  def deleteEmployee(empId: String): Future[Option[Int]]
}

@Singleton
 object AppDAOImpl extends AppDAO {
  implicit val timeout = Timeout(10, TimeUnit.SECONDS)

  def getEmployee(empId: String): Future[Option[Employee]] = Future {
    Logger.info(s"AppDao: getEmployee with $empId")
    val getStatement = QueryBuilder.select().from("Employee")
      .where(QueryBuilder.eq("empid", empId))
    val result = CassandraClient.getSession().execute(getStatement)
    if(!result.isExhausted){
      val row = result.one()
      val emp = Employee(row.getString("empid"), row.getString("name"), row.getInt("age"), Option(row.getString("designation")))
      Some(emp)
    } else None
  }

  def addEmployee(employee: Employee): Future[Option[Int]] = Future{
    Logger.info(s"AppDao: addEmployee")
    val addStatement = QueryBuilder.insertInto("Employee")
      .value("empid", employee.empId)
      .value("name", employee.name)
      .value("age", employee.age)
      .value("designation", employee.Designation.getOrElse(null))
    CassandraClient.getSession().execute(addStatement)
    Some(CREATED)
  }

  def deleteEmployee(empId: String): Future[Option[Int]] = Future{
    Logger.info(s"AppDao: deleteEmployee with $empId")
    val deleteStatement = QueryBuilder.delete().from("Employee")
      .where(QueryBuilder.eq("empid", empId))
    val result = CassandraClient.getSession().execute(deleteStatement)
    Some(OK)
  }
}
