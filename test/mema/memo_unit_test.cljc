(ns mema.memo-unit-test
  (:require [mema.memo-unit :as sut]
            [mema.chronos]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))

(def initial-card {:q "day week" :a 7})

(def ts-2015-1-1 (mema.chronos/ts-ymd 2015 1 1))
(def ts-2015-1-2 (mema.chronos/ts-ymd 2015 1 2))

(def flash-card-0
  {:card {:q "day week" :a 7}
   :answers ()
   :sr {:ef 250
        :ir 0}})

(def flash-card-1
  {:card {:q "day week" :a 7}
   :answers (list {:score 4 :ts ts-2015-1-1})
   :sr {:ef 250
        :ts ts-2015-1-1
        :ir 1}})

(t/deftest test-init-card
  (t/testing "test the creation of a flash card"
    (t/is (= flash-card-0 (sut/create initial-card)))))

(t/deftest test-add-answers
  (t/testing "answers"
    (t/is (= flash-card-1 (sut/add-answer flash-card-0 {:score 4 :ts ts-2015-1-1})))))
