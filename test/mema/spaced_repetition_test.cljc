(ns mema.spaced-repetition-test
  (:require #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])
            [mema.spaced-repetition :as sut]
            [mema.chronos]))

(def ts-2015-1-1 (mema.chronos/ts-ymd 2015 1 1))
(def ts-2015-1-2 (mema.chronos/ts-ymd 2015 1 2))

(t/deftest mod-ef-test
  (t/testing "mod easy factor"
    (t/is (= -80 (sut/add-to-ef 0)))
    (t/is (= -54 (sut/add-to-ef 1)))
    (t/is (= -32 (sut/add-to-ef 2)))
    (t/is (= -14 (sut/add-to-ef 3)))
    (t/is (= 0 (sut/add-to-ef 4)))
    (t/is (= 10 (sut/add-to-ef 5)))))

;; (t/deftest evolution
;;   (t/testing "evolution of spaced repetition intervals"
;;     (let [sr0 {:ef 250 :ir 0}
;;           sr0-5 {:ef 250 :ir 1}
;;           sr0-3 {:ef 236 :ir 1}
;;           sr1 {:ef 200 :ir 1}
;;           sr1-4 {:ef 200 :ir 6}
;;           sr1-2 {:ef 168 :ir 0}
;;           sr2 {:ef 200 :ir 6}
;;           sr2-5 {:ef 210 :ir 13}
;;           ]
;;       (t/is (= sr0-5 (sut/update-q sr0 5)))
;;       (t/is (= sr0-3 (sut/update-q sr0 3)))
;;       (t/is (= sr1-4 (sut/update-q sr1 4)))
;;       (t/is (= sr1-2 (sut/update-q sr1 2)))
;;       (t/is (= sr2-5 (sut/update-q sr2 5)))
;;       )))

(t/deftest evolution2
  (t/testing "evolution of spaced repetition intervals"
    (let [sr0 {:ef 250 :ir 0}
          sr0-5 {:ef 250 :ir 1 :ts 1}
          sr0-3 {:ef 236 :ir 1 :ts 2}
          sr1 {:ef 200 :ir 1}
          sr1-4 {:ef 200 :ir 6 :ts 1}
          sr1-2 {:ef 168 :ir 0 :ts 2}
          sr2 {:ef 200 :ir 6}
          sr2-5 {:ef 210 :ir 13 :ts 1}
          ]
      (t/is (= sr0-5 (sut/update-with-answer sr0 {:score 5 :ts 1})))
      (t/is (= sr0-3 (sut/update-with-answer sr0 {:score 3 :ts 2})))
      (t/is (= sr1-4 (sut/update-with-answer sr1 {:score 4 :ts 1})))
      (t/is (= sr1-2 (sut/update-with-answer sr1 {:score 2 :ts 2})))
      (t/is (= sr2-5 (sut/update-with-answer sr2 {:score 5 :ts 1})))
      )))

(t/deftest test-quality-decimals
  (t/is (< 0 (sut/add-to-ef 4.5) 10))
  (t/is (< -14 (sut/add-to-ef 3.5) 0))
  )

(def sr-due (sut/update-with-answer sut/initial {:score 5 :ts ts-2015-1-1}))

(t/deftest test-new?
  (t/testing "is-new-card"
    (t/is (sut/new? sut/initial))
    (t/is (not (sut/new? sr-due)))))

(t/deftest test-next-ts
  (t/is (= 0 (sut/next-ts sut/initial)))
  (t/is (= (mema.chronos/ts-ymd 2015 1 2) (sut/next-ts sr-due))))

(t/deftest test-repeat
  (t/is (= true (sut/repeat? (sut/update-with-answer sut/initial {:score 1 :ts ts-2015-1-1})))))

(t/deftest test-due?
  (t/is (= true (sut/due? sr-due ts-2015-1-2 ts-2015-1-2)))
  (t/testing "repeat cards are no due --- they are repeat!!"
    (t/is (= false (sut/due?
                    (sut/update-with-answer sut/initial {:score 1 :ts ts-2015-1-1})
                    ts-2015-1-1
                    ts-2015-1-2)))))
