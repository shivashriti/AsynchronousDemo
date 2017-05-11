
// @GENERATOR:play-routes-compiler
// @SOURCE:F:/projects/AsynchronousDemo/conf/routes
// @DATE:Thu May 11 23:18:39 IST 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:5
  ApplicationController_0: controllers.ApplicationController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:5
    ApplicationController_0: controllers.ApplicationController
  ) = this(errorHandler, ApplicationController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, ApplicationController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/employees/""" + "$" + """empId<[^/]+>""", """controllers.ApplicationController.getEmployees(empId:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/employees""", """controllers.ApplicationController.addEmployee"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/employees/""" + "$" + """empId<[^/]+>""", """controllers.ApplicationController.deleteEmployee(empId:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:5
  private[this] lazy val controllers_ApplicationController_getEmployees0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/employees/"), DynamicPart("empId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_getEmployees0_invoker = createInvoker(
    ApplicationController_0.getEmployees(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "getEmployees",
      Seq(classOf[String]),
      "GET",
      """ These are example routes used to show the asynchronous call. They are basic operations on Employee data""",
      this.prefix + """api/employees/""" + "$" + """empId<[^/]+>"""
    )
  )

  // @LINE:6
  private[this] lazy val controllers_ApplicationController_addEmployee1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/employees")))
  )
  private[this] lazy val controllers_ApplicationController_addEmployee1_invoker = createInvoker(
    ApplicationController_0.addEmployee,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "addEmployee",
      Nil,
      "POST",
      """""",
      this.prefix + """api/employees"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_ApplicationController_deleteEmployee2_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/employees/"), DynamicPart("empId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ApplicationController_deleteEmployee2_invoker = createInvoker(
    ApplicationController_0.deleteEmployee(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "deleteEmployee",
      Seq(classOf[String]),
      "DELETE",
      """""",
      this.prefix + """api/employees/""" + "$" + """empId<[^/]+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:5
    case controllers_ApplicationController_getEmployees0_route(params) =>
      call(params.fromPath[String]("empId", None)) { (empId) =>
        controllers_ApplicationController_getEmployees0_invoker.call(ApplicationController_0.getEmployees(empId))
      }
  
    // @LINE:6
    case controllers_ApplicationController_addEmployee1_route(params) =>
      call { 
        controllers_ApplicationController_addEmployee1_invoker.call(ApplicationController_0.addEmployee)
      }
  
    // @LINE:7
    case controllers_ApplicationController_deleteEmployee2_route(params) =>
      call(params.fromPath[String]("empId", None)) { (empId) =>
        controllers_ApplicationController_deleteEmployee2_invoker.call(ApplicationController_0.deleteEmployee(empId))
      }
  }
}
