
// @GENERATOR:play-routes-compiler
// @SOURCE:F:/projects/AsynchronousDemo/conf/routes
// @DATE:Thu May 11 23:18:39 IST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
