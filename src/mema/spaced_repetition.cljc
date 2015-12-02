(ns mema.spaced-repetition)

;; ef easy factor.
;; Ir interval repetition (usually in days)
(def initial {:ef 2.5 :ir 0})

;;(+ -0.8M (* q 0.28M) (* q q -0.02M))
(defn add-to-ef [q]
  (condp = q
    0 -0.8
    1 -0.54
    2 -0.32
    3 -0.14
    4 0
    5 0.1))

(defn new-ef [ef q]
  (max 1.1 (min 2.5 (+ ef (add-to-ef q)))))

(defn round-up [n]
  (let [i (int n)]
    (if (> n i) (inc i) i)))

(defn next-interval [ir ef q]
  (if (< q 3) 0
      (cond
        (= ir 0) 1
        (= ir 1) 6
        :else (round-up (* ef ir)))))

(defn update-q [{:keys [ir ef] :as sr} q]
  (let  [n-ef (new-ef ef q)]
    (assoc sr
           :ir (next-interval ir n-ef q)
           :ef n-ef)))
