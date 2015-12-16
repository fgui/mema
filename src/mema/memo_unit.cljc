(ns mema.memo-unit
  (:require [mema.spaced-repetition :as sr]))

(defn create [card]
  {:card card
   :answers ()
   :sr sr/initial})

(defn add-answer [fc answer]
  (-> fc
      (update :answers conj answer)
      (update :sr sr/update-with-answer answer)))

(defn id [fc] (-> fc :card :id))

(defn new? [mu] (sr/new? (:sr mu)))
(defn repeat? [mu] (sr/repeat? (:sr mu)))
(defn due? [mu] (sr/due? (:sr mu)))
