(ns topherizer.extend
  (:import (java.lang System)))

(defn env [s] (System/getenv s))

(defn prefix [ s items]
  (map #(str s %1) items))
