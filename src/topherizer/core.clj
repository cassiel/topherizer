(ns topherizer.core
  (:require [topherizer.manifest :as m])
  (:gen-class))

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
  (load-string (format "%s (topherizer.core/process %s)" m/PREFIX s)))

(defn do-file
  [in-f out-f]
  (println (format "%s -> %s" in-f out-f))
  (spit out-f (str m/BANNER "\n\n"
                 (clojure.string/join "\n" (do-string (slurp in-f))) "\n")))

(defn replace-extension
  [f new-ext]
  (clojure.string/replace-first f #"(\.\w+)?$" new-ext))

(defn -main
  ([in-f out-f]
     (do-file in-f out-f))
  ([in-f]
     (let [out-f (replace-extension in-f ".txt")]
       (if (= in-f out-f)
         (throw (IllegalArgumentException. (str "attempting to overwrite " in-f)))
         (do-file in-f out-f)))))
