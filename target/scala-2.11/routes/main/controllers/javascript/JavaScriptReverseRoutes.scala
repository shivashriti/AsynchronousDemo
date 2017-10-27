
// @GENERATOR:play-routes-compiler
// @SOURCE:F:/projects/git/Base/conf/routes
// @DATE:Fri Oct 27 12:27:42 IST 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:5
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:5
  class ReverseApplicationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def deleteEmployee: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.deleteEmployee",
      """
        function(empId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/employees/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("empId", encodeURIComponent(empId0))})
        }
      """
    )
  
    // @LINE:6
    def addEmployee: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.addEmployee",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/employees"})
        }
      """
    )
  
    // @LINE:5
    def getEmployees: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.getEmployees",
      """
        function(empId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/employees/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("empId", encodeURIComponent(empId0))})
        }
      """
    )
  
  }


}
