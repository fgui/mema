(ns mema.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [mema.spaced-repetition-test]))

(doo-tests 'mema.spaced-repetition-test)
