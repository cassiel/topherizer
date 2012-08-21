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

In its simplest form, the Clojure code is a list (vector) of items, in square
brackets, pairing tags with string values. A straight translation of the
original text would look like this:

```clojure
[:max.system.jar.dir	"/Media/MaxJARs/support/jython2.5.2"
 :max.system.jar.dir	"/Media/MaxJARs/support/utility"
 :max.system.jar.dir	"/Media/MaxJARs/support/jetty"
 :max.system.jar.dir	"/Media/MaxJARs/support/groovy"
 :max.system.jar.dir	"/Media/MaxJARs/support/clojure"

 :max.system.class.dir	"/Users/nick/workspace/Flet/ModularInstrument/SYSTEM/_classes"

 :max.system.class.dir	"/Users/nick/workspace/MaxMSP/DEVELOPMENT_0/mxj-development/straker/java/.classes"

 :max.system.jar.dir	"/Media/MaxJARs/loadbang"

 :max.system.class.dir "/Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/clj-src"
 :max.system.class.dir "/Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/_classes"

 :max.jvm.option "-Xincgc"
 :max.jvm.option "-Xms64m"
 :max.jvm.option "-Xmx256m"

 :max.jvm.option "-Xdebug"
 :max.jvm.option "-Xnoagent"
 :max.jvm.option "-Xrunjdwp:transport=dt_socket,address=8074,server=y,suspend=n"
 :max.jvm.option "-XX:-UseSharedSpaces"]
```

The items in the list are, alternately, a symbol for the option name
(with a leading "`:`", Clojure's syntax for keywords) and a string for
the value of the option. It makes sense to put each option alongside its value
on one line, but Clojure is insensitive to line breaks.

By the way: note that the commas in the `:max.jvm.option` debug line
aren't escaped with backslashes, although they need to be in the final
file; Topherizer takes care of this.

## Second Cut: Grouping

It seems a bit daft to specify the same option name again and again, so
option values can be grouped into a list:

```clojure
[;; System JAR dirs together:
 :max.system.jar.dir ["/Media/MaxJARs/support/jython2.5.2"
                      "/Media/MaxJARs/support/utility"
                      "/Media/MaxJARs/support/jetty"
                      "/Media/MaxJARs/support/groovy"
                      "/Media/MaxJARs/support/clojure"
                      "/Media/MaxJARs/loadbang"]

 ;; Assorted classes:
 :max.system.class.dir ["/Users/nick/workspace/Flet/ModularInstrument/SYSTEM/_classes"
                        "/Users/nick/workspace/MaxMSP/DEVELOPMENT_0/mxj-development/straker/java/.classes"
                        "/Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/clj-src"
                        "/Users/nick/workspace/Flet/ModularInstrument/DYNAMIC/_classes"]

 ;; Debugger setup:
 :max.jvm.option ["-Xincgc"
                  "-Xms64m"
                  "-Xmx256m"

                  "-Xdebug"
                  "-Xnoagent"
                  "-Xrunjdwp:transport=dt_socket,address=8074,server=y,suspend=n"
                  "-XX:-UseSharedSpaces"]]
```

(At this stage you really should be thinking of finding yourself a
decent Clojure editor.)

The resulting `.txt` file from this script is equivalent to the one from
the first example; the only difference is the line ordering (since we
moved a `:max.system.jar.dir` in the input).

Clojure comments (indicated by `;;`) are not not passed to the output.

## Third Cut: Common Prefixes

Some of the input strings in the last example are rather long and have
the same leading characters, so we can lift that prefix out with a
built-in `prefix` function:

```clojure
[;; System JAR dirs together:
 :max.system.jar.dir [(prefix "/Media/MaxJARs/support/" ["jython2.5.2"
                                                         "utility"
                                                         "jetty"
                                                         "groovy"
                                                         "clojure"])
                      "/Media/MaxJARs/loadbang"]

 ;; Assorted classes:
 :max.system.class.dir [;; Stuff in workspace:
                        (prefix "/Users/nick/workspace/"
                                ["MaxMSP/DEVELOPMENT_0/mxj-development/straker/java/.classes"
                                 ;; Stuff in workspace and ModularInstrument:
                                 (prefix "Flet/ModularInstrument/"
                                         ["SYSTEM/_classes"
                                          "DYNAMIC/clj-src"
                                          "DYNAMIC/_classes"])])]

 ;; Debugger setup:
 :max.jvm.option [(prefix "-X" ["incgc"
                                "ms64m"
                                "mx256m"

                                "debug"
                                "noagent"
                                "runjdwp:transport=dt_socket,address=8074,server=y,suspend=n"])
                  "-XX:-UseSharedSpaces"]]

```
