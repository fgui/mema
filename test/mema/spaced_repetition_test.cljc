(ns mema.spaced-repetition-test
  (:require #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])
            [mema.spaced-repetition :as sut]))

(t/deftest mod-ef-test
  (t/testing "mod easy factor"
    (t/is (= -0.8 (sut/add-to-ef 0)))
    (t/is (= -0.54 (sut/add-to-ef 1)))
    (t/is (= -0.32 (sut/add-to-ef 2)))
    (t/is (= -0.14 (sut/add-to-ef 3)))
    (t/is (= 0 (sut/add-to-ef 4)))
    (t/is (= 0.1 (sut/add-to-ef 5)))))

(t/deftest evolution
  (t/testing "evolution of spaced repetition intervals"
    (let [sr0 sut/initial
          sr0-5 {:ef 2.5 :ir 1}
          sr0-3 {:ef 2.36 :ir 1}
          sr1 {:ef 2 :ir 1}
          sr1-4 {:ef 2 :ir 6}
          sr1-2 {:ef 1.68 :ir 0}
          sr2 {:ef 2 :ir 6}
          sr2-5 {:ef 2.1 :ir 13}
          ]
      (t/is (= sr0-5 (sut/update-q sr0 5)))
      (t/is (= sr0-3 (sut/update-q sr0 3)))
      (t/is (= sr1-4 (sut/update-q sr1 4)))
      (t/is (= sr1-2 (sut/update-q sr1 2)))
      (t/is (= sr2-5 (sut/update-q sr2 5)))
      )))
