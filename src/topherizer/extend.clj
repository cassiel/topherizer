(ns topherizer.extend
  (:import (java.lang System)))

(defn env [s] (System/getenv s))

(defn prefix [s item]
  (if (instance? String item)
    (str s item)
    (map #(str s %1) (flatten item))))
