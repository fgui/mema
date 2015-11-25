(ns mema.core)

(defn create-flash-card [card]
  {:card card
   :answers ()
   :sr  {:ef 2.5
         :ts nil
         :days 0}
   })

(defn add-to-ef [q]
  (condp = q
    0 -0.8
    1 -0.54
    2 -0.32
    3 -0.14
    4 0
    5 0.1)
  ;;(+ -0.8M (* q 0.28M) (* q q -0.02M))
  )

(defn new-ef [ef q]
  (max 1.1 (min 2.5 (+ ef (add-to-ef q))))
  )

(defn round-up [n]
  (let [i (int n)]
    (if (> n i) (inc i) i))
  )

(defn next-days [days ef q]
  (if (< q 3) 0
      (cond
        (= days 0) 1
        (= days 1) 6
        :else (round-up (* ef days))))
  )

(defn update-sr [{:keys [days ef] :as sr} {:keys [ts q] :as answer}]
  (let  [n-ef (new-ef ef q)]
    (assoc sr
           :ts ts
           :days (next-days days n-ef q)
           :ef n-ef)
    )
  )

(defn answer-fc [fc {:keys [ts q] :as answer}]
  (-> fc
      (update :answers conj answer)
      (update :sr update-sr answer)))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
