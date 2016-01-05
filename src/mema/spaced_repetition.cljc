(ns mema.spaced-repetition
  "implements sm2 of spaced repetitions"
  (:require [mema.answer :as a]))

(def millis-day (* 24 60 60 1000))

;; ef easy factor. (* 100)
;; ir interval repetition (usually in days)
(def initial {:ef 250 :ir 0})

(defn add-to-ef [q]
  (+ -80 (* q 28) (* q q -2)))

(defn- new-ef [ef q]
  (max 110 (min 250 (+ ef (add-to-ef q)))))

(defn- round-up [n]
  (let [i (int n)]
    (if (> n i) (inc i) i)))

(defn- next-interval [ir ef q]
  (if (< q 3) 0
      (cond
        (= ir 0) 1
        (= ir 1) 6
        :else (round-up (* (/ ef 100) ir)))))

;;; public api

(defn update-with-answer [{:keys [ir ef] :as sr} answer]
  (let  [score (max (min (a/score answer) 5) 0)
         n-ef (new-ef ef score)]
    (assoc sr
           :ts (a/ts answer)
           :ir (next-interval ir n-ef score)
           :ef n-ef)))

(defn new? [sr]
  (nil? (:ts sr)))

(defn next-ts [sr]
  (if (new? sr) 0
      (+ (:ts sr) (* millis-day (:ir sr)))))

(defn repeat? [sr]
  (and (not (new? sr))
       (= 0 (:ir sr))))

(defn due? [sr ts-from ts-to]
  (and (not (new? sr))
       (not (repeat? sr))
       (<= ts-from (next-ts sr) ts-to)))
