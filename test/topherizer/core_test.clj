(ns topherizer.core-test
  (:require (topherizer [core :as t]))
  (:use [expectations]))

(defn pad [[a b]] (format "%-30s%s" a b))

(defn padder [& args] (map pad args))

(expect (padder ["a" "A"] ["b" "B"])
        (t/do-string "[:a \"A\" :b \"B\"]"))

(expect (padder ["a" "A"] ["a" "B"] ["a" "C"])
        (t/do-string "[:a [\"A\" \"B\" \"C\"]]"))

(expect (padder ["a" "a\\,b"])
        (t/do-string "[:a \"a,b\"]"))

(expect []
        (t/do-string "[:a nil]"))

(expect (padder ["a" "A"] ["b" "X"] ["b" "Y"])
        (t/do-string "[:a \"A\" :b [\"X\" nil \"Y\"]]"))
