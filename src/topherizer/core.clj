(ns topherizer.core)

(def BANNER "; This file created with topherizer - https://github.com/cassiel/topherizer ;")

(def PREFIX "(ns user (:use [topherizer.extend :only [env prefix]]))")

(defn escape-commas [s] (clojure.string/replace s #"," "\\\\,"))

(defn process-item
  [[tag entries]] (map (fn [e] (format "%-30s%s"
                                      (clojure.core/name tag)
                                      (escape-commas e))) (flatten [entries]))
  )

(defn process
  [input]
  (flatten (map process-item (partition 2 input))))

(defn do-string
  [s]
  (load-string (format "%s (topherizer.core/process %s)" PREFIX s)))

(defn do-file
  [in-f out-f]
  (spit out-f (str BANNER "\n\n"
                   (clojure.string/join "\n" (do-string (slurp in-f))) "\n")))


(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
