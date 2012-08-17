(let
    [DEBUG true
     HOME (env "HOME")]

  [
   :max.system.jar.dir (prefix "/Media/MaxJARS/support/" ["jython2.5.2"
                                                          "utility"
                                                          "jetty"
                                                          "groovy"
                                                          "clojure"])])
