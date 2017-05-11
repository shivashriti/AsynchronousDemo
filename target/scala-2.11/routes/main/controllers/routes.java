
// @GENERATOR:play-routes-compiler
// @SOURCE:F:/projects/AsynchronousDemo/conf/routes
// @DATE:Thu May 11 23:18:39 IST 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseApplicationController ApplicationController = new controllers.ReverseApplicationController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseApplicationController ApplicationController = new controllers.javascript.ReverseApplicationController(RoutesPrefix.byNamePrefix());
  }

}
