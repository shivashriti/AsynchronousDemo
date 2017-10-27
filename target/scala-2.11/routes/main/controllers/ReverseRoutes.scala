
// @GENERATOR:play-routes-compiler
// @SOURCE:F:/projects/git/Base/conf/routes
// @DATE:Fri Oct 27 12:27:42 IST 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:5
package controllers {

  // @LINE:5
  class ReverseApplicationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def deleteEmployee(empId:String): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "api/employees/" + implicitly[PathBindable[String]].unbind("empId", dynamicString(empId)))
    }
  
    // @LINE:6
    def addEmployee(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/employees")
    }
  
    // @LINE:5
    def getEmployees(empId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/employees/" + implicitly[PathBindable[String]].unbind("empId", dynamicString(empId)))
    }
  
  }


}
