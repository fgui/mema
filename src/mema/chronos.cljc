(ns mema.chronos)

(def millis-day (* 24 60 60 1000))
(def period-ms millis-day)

(defn ts-ymd [y m d]
  #?(:cljs (.getTime (js/Date. y m d))
     :clj (.getTime (java.util.Date. (- y 1900) m d)) ;; 00:00 UTC
     ))

(defn ts-now []
  #?(:cljs (.getTime (js/Date.))
     :clj (System/currentTimeMillis))
  )

(defn period-for-ts [ts]
  (* millis-day (int (/ ts millis-day))))

(defn period-now [] (period-for-ts (ts-now)))
