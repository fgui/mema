(ns mema.flash-card-test
  (:require [mema.flash-card :as sut]
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
   :answers (list {:q 4 :ts ts-2015-1-1})
   :ts ts-2015-1-1
   :sr {:ef 250
        :ir 1}})

(t/deftest test-init-card
  (t/testing "test the creation of a flash card"
    (t/is (= flash-card-0 (sut/create initial-card)))))

(t/deftest test-add-answers
  (t/testing "answers"
    (t/is (= flash-card-1 (sut/add-answer flash-card-0 {:q 4 :ts ts-2015-1-1})))))

(t/deftest test-new?
  (t/testing "is-new-card"
    (t/is (sut/new? (sut/create initial-card)))
    (t/is (not (sut/new? flash-card-1)))))

(t/deftest test-next-ts
  (t/is (= 0 (sut/next-ts  flash-card-0)))
  (t/is (= (mema.chronos/ts-ymd 2015 1 2) (sut/next-ts  flash-card-1))))

(t/deftest test-repeat
  (t/is (= true (sut/repeat? (sut/add-answer flash-card-0 {:q 1 :ts ts-2015-1-1})))))

(t/deftest test-due?
  (t/is (= true (sut/due? flash-card-1 ts-2015-1-2 ts-2015-1-2)))
  (t/testing "repeat cards are no due --- they are repeat!!"
    (t/is (= false (sut/due?
                    (sut/add-answer flash-card-0 {:q 1 :ts ts-2015-1-1})
                    ts-2015-1-1
                    ts-2015-1-2)))))
