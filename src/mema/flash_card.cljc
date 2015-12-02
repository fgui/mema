(ns mema.flash-card
  (:require [mema.spaced-repetition :as sr]))


(def millis-day (* 24 60 60 1000))

(defn create [card]
  {:card card
   :answers ()
   :sr sr/initial
   })

(defn add-answer [fc {:keys [ts q] :as answer}]
  (-> fc
      (assoc :ts ts)
      (update :answers conj answer)
      (update :sr sr/update-q q)))

(defn new? [fc]
  (nil? (:ts fc))
  )

(defn next-ts [fc]
  (if (new? fc) 0
      (+ (:ts fc) (* millis-day (-> fc :sr :ir))))
  )