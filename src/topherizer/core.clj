(ns topherizer.core)

(def PREFIX "(ns user (:use [topherizer.extend :only [env prefix]]))")

(defn process
  [input]
  (partition 2 input))

(defn do-string
  [s]
  (load-string (format "%s (topherizer.core/process %s)" PREFIX s)))

(defn do-file
  [f]
  (do-string (slurp f)))


(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
