(ns mema.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [mema.spaced-repetition-test]
            [mema.flash-card-test]
            [mema.deck-test]))

(doo-tests 'mema.spaced-repetition-test
           'mema.flash-card-test
           'mema.deck-test)
