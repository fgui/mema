(ns mema.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [mema.spaced-repetition-test]
            [mema.memo-unit-test]
            [mema.chronos-test]
            [mema.deck-test]))

(doo-tests 'mema.spaced-repetition-test
           'mema.memo-unit-test
           'mema.deck-test
           'mema.chronos-test)
