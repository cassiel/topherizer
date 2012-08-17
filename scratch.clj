(ns user
  (:require [topherizer.core :as t]))

(t/do-file "examples/test.clj")

(System/getenv "HOME")

(System/nanoTime)


(defn foo [a b c] [c b a])

(foo [1 2 3])
