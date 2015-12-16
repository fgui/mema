(ns mema.deck
  (:require [mema.memo-unit :as mu]))

(defn create [name memo-units]
  {:name name
   :cards (zipmap (range) memo-units)})

(defn- filter-cards [fun] (fn [deck] (filter fun (:cards deck))))

(def new-cards (filter-cards mu/new?))
(def repeat-cards (filter-cards mu/repeat?))
(def due-cards (filter-cards mu/due?))

(defn add-answer [deck id answer]
  (update-in deck [:cards id] #(mu/add-answer % answer)))
