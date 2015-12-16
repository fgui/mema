(ns mema.deck-test
  (:require [mema.deck :as sut]
            [mema.memo-unit :as fc]
            [mema.spaced-repetition :as sr]
            [mema.chronos :as c]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))

(def new-deck (vec (repeat 10 (fc/create {:tag "my-card"}))))

(def one-repeat-nine-new (update new-deck 0 fc/add-answer {:score 2 :ts 1}))

(def one-2015-1-2 (update new-deck 1 fc/add-answer {:score 5 :ts (c/ts-ymd 2015 1 1)}))

(def ts-2015-1-2 (c/ts-ymd 2015 1 2))

(t/deftest test-filters
  (t/testing "new?"
    (t/is (= 10 (count (filter #(-> % :sr sr/new?) new-deck))))
    (t/is (= 9 (count (filter #(-> % :sr sr/new?) one-repeat-nine-new)))))
  (t/testing "repeat?"
    (t/is (= 0 (count (filter #(-> % :sr sr/repeat?) new-deck))))
    (t/is (= 1 (count (filter #(-> % :sr sr/repeat?) one-repeat-nine-new)))))
  (t/testing "due?"
    (t/is (= 1 (count (filter #(sr/due? (:sr %) ts-2015-1-2 ts-2015-1-2) one-2015-1-2))))
    (t/is (= 1 (count (filter #(sr/due? (:sr %) 0 ts-2015-1-2) one-2015-1-2))))))
