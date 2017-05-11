package utils

import play.api.libs.json.Json

/*This is used to serialize case classes to json.
We can define our case classes and create json read/write format for them.
This is very useful for request format validations and responses*/

/**
  * Created by Shiva on 20/04/2017.
  */

trait JsonResponse

object JsonResponse {
  case class Employee(empId: String, name: String, age: Int, Designation: Option[String]) extends JsonResponse

  object Employee {
    implicit val format = Json.format[Employee]
  }

}

