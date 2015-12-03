(ns mema.chronos)

(defn ts-ymd [y m d]
  #?(:cljs (.getTime (js/Date. y m d))
     :clj (.getTime (java.util.Date. (- y 1900) m d)) ;; 00:00 UTC
     ))
