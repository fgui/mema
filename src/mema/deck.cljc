(ns mema.deck
  (:require [mema.flash-card :as fc]))

(defn create [name flash-cards]
  {:name name
   :cards (zipmap (range) flash-cards)})

(defn- filter-cards [fun] (fn [deck] (filter fun (:cards deck))))

(def new-cards (filter-cards fc/new?))
(def repeat-cards (filter-cards fc/repeat?))
(def due-cards (filter-cards fc/due?))

(defn add-answer [deck id answer]
  (update-in deck [:cards id] #(fc/add-answer % answer)))
