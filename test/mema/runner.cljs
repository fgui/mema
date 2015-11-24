(ns mema.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [mema.core-test]))

(doo-tests 'mema.core-test)
