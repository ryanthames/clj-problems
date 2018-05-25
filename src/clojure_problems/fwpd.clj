(ns clojure-problems.fwpd)

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])
(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into a row of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(glitter-filter 3 (mapify (parse (slurp filename))))

;; 1. Turn the result of your glitter filter into a list of names.
(map :name (glitter-filter 3 (mapify (parse (slurp filename)))))

;; 2. Write a function, append, which will append a new suspect to your list of suspects.
(defn append
  [suspects new-suspect]
  (conj suspects new-suspect))

(def suspects (glitter-filter 3 (mapify (parse (slurp filename)))))
(append suspects {:name "Some Vampire" :glitter-index 4})

;; 3. Write a function, validate, which will check that :name and :glitter-index are present when you append. The validate function should accept two arguments: a map of keywords to validating functions, similar to conversions, and the record to be validated.
(def validators {:name string? :glitter-index number?})
(defn validate
  [vampire]
  (reduce
    (fn
      [is-valid vamp-key]
      (let [not-nil? #((complement nil?) %)]
        (and is-valid (not-nil? (get vampire vamp-key)) ((get validators vamp-key) (get vampire vamp-key)))))
    true
    vamp-keys))

;; 4. Write a function that will take your list of maps and convert it back to a CSV string. You’ll need to use the clojure.string/join function.
(defn to-csv
  [suspects]
  (map #(clojure.string/join "," [(:name %) (:glitter-index %)]) suspects))

(to-csv (glitter-filter 3 (mapify (parse (slurp filename)))))

