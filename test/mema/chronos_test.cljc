(ns mema.chronos-test
  (:require [mema.chronos :as sut]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))

(t/deftest ts-day-test
  (let [today-ts (sut/period-now)]
    (t/is (> (sut/ts-now) today-ts))
    (t/is (< (- (sut/ts-now) sut/millis-day) today-ts))
    )
  )
