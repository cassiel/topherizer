# Tutorial

We'll aim to generate the same output from all of the input scripts
presented here. The output (traditionally maintained by hand), complete
with comments, looks a bit like this:

```
; This is the config used by Max when instantiating the JVM ;
; Comments are enclosed within 2 semicolons ;

; The file format is relatively fragile so pay attention ;
; when editing this file !! ;

; some example options one may use ;

; add all jars in /Users/topher/java/lib to the system classpath i.e. immutable classpath ;
; max.system.jar.dir /Users/topher/java/lib;

;-- *** NICK *** --;
max.system.jar.dir	/Media/MaxJARs/support/jython2.5.2
max.system.jar.dir	/Media/MaxJARs/support/utility
max.system.jar.dir	/Media/MaxJARs/support/jetty
max.system.jar.dir	/Media/MaxJARs/support/groovy
max.system.jar.dir	/Media/MaxJARs/support/clojure

max.system.class.dir	/Users/nick/workspace/Flet/ModularInstrument/SYSTEM/_classes

max.system.class.dir	/Users/nick/workspace/MaxMSP/DEVELOPMENT_0/mxj-development/straker/java/.classes

;-- Don't need the loadbang libs if we're loading from Eclipse directories: --;
max.system.jar.dir	/Media/MaxJARs/loadbang

; add /Users/topher/foo to the system classpath i.e. immutable classpath ;
; max.system.class.dir /Users/topher/foo      ;

; these next 2 are typically what you would want to use if you want additional directories ;
; added to your classpath for max ;

; add /Users/topher/myclasses to the dynamic classpath of MXJClassLoader ;
; max.dynamic.class.dir /Users/topher/myclasses ;


;max.dynamic.class.dir /Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/_classes;

; In order to see Clojure source tree (first, before it's copied to _classes?): ;
max.system.class.dir /Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/clj-src
max.system.class.dir /Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/_classes

; add all jars in /Users/topher/myjars to the dynamic classpath of MXJClassLoader ;
;max.dynamic.jar.dir /Users/topher/myjars     ;


; specify some jvm options ;
max.jvm.option -Xincgc
max.jvm.option -Xms64m
max.jvm.option -Xmx256m

; uncomment these options(i.e. remove surrounding semi colons ;
; to cause the JVM to be created ;
; in debug mode and listening for remote debugger connections ;
; on port 8074. This would enable you to interactively debug ;
; your mxj code using JDB or some other debugger which supports ;
; the JDI wire protocol ;

max.jvm.option -Xdebug
max.jvm.option -Xnoagent
max.jvm.option -Xrunjdwp:transport=dt_socket\,address=8074\,server=y\,suspend=n
max.jvm.option -XX:-UseSharedSpaces
```

## First Cut: Straight Translation



(All these files are in the `examples` folder.)
