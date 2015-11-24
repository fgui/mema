(ns mema.core-test
  (:require #?(:clj [clojure.test :as t]
              :cljs [cljs.test :as t :include-macros true])
            [mema.core :as sut]))

(t/deftest a-test
  (t/testing "FIXME, I fail."
    (t/is (= 2 1))))
