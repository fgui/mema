(ns mema.core
  (:require [mema.deck :as deck]
            [mema.flash-card :as fc]
            [mema.front-back-card :as fbc]))

(defn mult-card [x y]
  (fbc/create (str x " x " y "=") (* x y))
  )

(defn mult-cards [n]
  (for [a (range n) b (range n)] (mult-card a b)))

(defn create-deck-mult [n]
  (deck/create
   (str "mult " n)
   (map fc/create (mult-cards n))
   ))
