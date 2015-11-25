(ns mema.core-test
  (:require #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])
            [mema.core :as sut]))

(def initial-card {:q "day week" :a 7})


(defn ts-dmy [y m d]
  #?(:cljs (.getTime (js/Date. y m d))
     :clj (.getTime (java.util.Date. (- y 1900) m d))
     ))

(def ts-2015-1-1 (ts-dmy 2015 1 1))


(def flash-card-0
  {:card {:q "day week" :a 7}
   :answers ()
   :sr {:ef 2.5
        :ts nil  ;; new no ts first answer
        :days 0}
   })

(def flash-card-1
  {:card {:q "day week" :a 7}
   :answers (list {:q 4 :ts ts-2015-1-1})
   :sr {:ef 2.5
        :ts ts-2015-1-1
        :days 1}
   })

(t/deftest test-init-card
  (t/testing "test the creation of a flash card"
    (t/is (= flash-card-0 (sut/create-flash-card initial-card)))))

(t/deftest mod-ef-test
(t/testing "mod easy factor"
(t/is (= -0.8 (sut/add-to-ef 0)))
(t/is (= -0.54 (sut/add-to-ef 1)))
(t/is (= -0.32 (sut/add-to-ef 2)))
(t/is (= -0.14 (sut/add-to-ef 3)))
(t/is (= 0 (sut/add-to-ef 4)))
(t/is (= 0.1 (sut/add-to-ef 5)))
))

(t/deftest test-answers
(t/testing "answers"
(t/is (= flash-card-1 (sut/answer-fc flash-card-0 {:q 4 :ts ts-2015-1-1}))))


)
