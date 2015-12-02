(ns mema.deck-test
  (:require [mema.deck :as sut]
            [mema.flash-card :as fc]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))

(def new-deck (repeat 10 (fc/create {:tag "my-card"})))
