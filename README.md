# topherizer

```clojure
(let
    [DEBUG false
     HOME (env "HOME")
     WORKSPACE (prefix HOME "/workspace/")]

  [
   :max.system.jar.dir (prefix "/Media/MaxJARS/support/" ["jython2.5.2"
                                                          "utility"
                                                          "jetty"
                                                          "groovy"
                                                          "clojure"])

   :max.system.class.dir
   (prefix WORKSPACE ["Flet/ModularInstrument/SYSTEM/_classes"
                      "MaxMSP/DEVELOPMENT_0/mxj-development/straker/java/.classes"])

   :max.system.jar.dir "/Media/MaxJARs/loadbang"

   :max.system.class.dir (prefix (str WORKSPACE "Flet/ModularInstrument/DYNAMIC/")
                                 ["clj-src" "_classes"])

   :max.jvm.option (prefix "-X" ["incgc" "ms64m" "mx256m"])


   :max.jvm.option (if DEBUG [(prefix "-X" ["debug"
                                            "oagent"
                                            "runjdwp:transport=dt_socket,address=8074,server=y,suspend=n"])
                              "-XX:-UseSharedSpaces"]
                       nil)])
```

Topherizer is a Java console application for generating the
`max.java.config.txt` file, which Max's Java users have, for the last
eight years, been painstakingly editing by hand. Topherizer allows the
configuration to be written in Clojure, with a cleaner structure,
conditional "statements" and some shortcut functions.

(It is arguable whether, at first glance, Clojure is an
improvement. Editing Clojure in a conventional text editor is a fiddly
procedure, but there are editors like Emacs (with Sam Aaron's
[emacs-live][el]) which make the procedure much more efficient.)

## Usage

FIXME

## License

Copyright © 2012 Nick Rothwell

Distributed under the Eclipse Public License, the same as Clojure.

[el]: https://github.com/overtone/emacs-live
