(ns mema.flash-card-test
  (:require [mema.flash-card :as sut]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))

(def initial-card {:q "day week" :a 7})

(defn ts-ymd [y m d]
  #?(:cljs (.getTime (js/Date. y m d))
     :clj (.getTime (java.util.Date. (- y 1900) m d))
     ))

(def ts-2015-1-1 (ts-ymd 2015 1 1))

(def flash-card-0
  {:card {:q "day week" :a 7}
   :answers ()
   :sr {:ef 2.5
        :ir 0}
   })

(def flash-card-1
  {:card {:q "day week" :a 7}
   :answers (list {:q 4 :ts ts-2015-1-1})
   :ts ts-2015-1-1
   :sr {:ef 2.5
        :ir 1}
   })

(t/deftest test-init-card
  (t/testing "test the creation of a flash card"
    (t/is (= flash-card-0 (sut/create initial-card)))))

(t/deftest test-answers
  (t/testing "answers"
    (t/is (= flash-card-1 (sut/add-answer flash-card-0 {:q 4 :ts ts-2015-1-1}))))
)

(t/deftest test-is-new
  (t/testing "is-new-card"
    (t/is (sut/new? (sut/create initial-card)))
    (t/is (not (sut/new? flash-card-1)))
    ))

(t/deftest test-next-ts
  (t/is (= 0 (sut/next-ts  flash-card-0)))
  (t/is (= (ts-ymd 2015 1 2) (sut/next-ts  flash-card-1)))
  )
