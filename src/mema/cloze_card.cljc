(ns mema.cloze-card)

(defn create [text blanks] [text blanks])
(defn text [c] (first c))
(defn blanks [c] (second c))
(def key text)
