(ns mema.spaced-repetition
  "implements sm2 of spaced repetitions"
  (:require [mema.answer :as a])
  )

;; ef easy factor. (* 100)
;; ir interval repetition (usually in days)
(def initial {:ef 250 :ir 0})

(defn add-to-ef [q]
  (+ -80 (* q 28) (* q q -2)))

(defn new-ef [ef q]
  (max 110 (min 250 (+ ef (add-to-ef q)))))

(defn round-up [n]
  (let [i (int n)]
    (if (> n i) (inc i) i)))

(defn next-interval [ir ef q]
  (if (< q 3) 0
      (cond
        (= ir 0) 1
        (= ir 1) 6
        :else (round-up (* (/ ef 100) ir)))))

(defn update-q [{:keys [ir ef] :as sr} q]
  (let  [n-ef (new-ef ef q)]
    (assoc sr
           :ir (next-interval ir n-ef q)
           :ef n-ef)))

(defn update-with-answer [{:keys [ir ef] :as sr} answer]
  (let  [score (max (min (a/score answer) 5) 0)
         n-ef (new-ef ef (a/score answer))]
    (assoc sr
           :ts (a/ts answer)
           :ir (next-interval ir n-ef score)
           :ef n-ef)))
