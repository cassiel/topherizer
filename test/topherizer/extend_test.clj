(ns topherizer.extend-test
  (:require (topherizer [extend :as e]))
  (:use [expectations]))

(expect "AB"
        (e/prefix "A" "B"))

(expect ["AB" "AC"]
        (e/prefix "A" ["B" "C"]))

(expect "ABC"
        (e/prefix "A" (e/prefix "B" "C")))

(expect ["AB" "AZC" "AZD"]
        (e/prefix "A" ["B" (e/prefix "Z" ["C" "D"])]))

(expect ["AB" "AZC" "AZqR" "AZqS" "AZD"]
        (e/prefix "A" ["B" (e/prefix "Z" ["C" (e/prefix "q" ["R" "S"]) "D"])]))
