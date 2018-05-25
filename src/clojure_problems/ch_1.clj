(ns clojure-problems.ch-1)

;; 1. use the str, vector, list, hash-map, and hash-set functions

(def name1 "Kenobi")
(str "General " name1)

(vector 1 2 3)
(vector "Hello" "There")

(list 5 6 7)

(hash-map :a 1 :b 2)

(hash-set :a 1 :b 2 5 1 1 1 6)

;; 2. Write a function that takes a number and adds 100 to it.

(defn add-100 [num] (+ num 100))
(add-100 5)

;; 3. Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
;(def dec9 (dec-maker 9))
;(dec9 10)
; => 1

(defn dec-maker
  "Returns a decrement function"
  [dec-value]
  #(- % dec-value))

(def dec9 (dec-maker 9))
(dec9 10)

;; 4. Write a function, mapset, that works like map except the return value is a set:
;(mapset inc [1 1 2 2])
; => #{2 3}

(defn mapset
  "Works like map but returns a set"
  [map-fn coll]
  (set (map map-fn coll)))

(mapset inc [1 1 2 2])

;; Create a function that’s similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.


