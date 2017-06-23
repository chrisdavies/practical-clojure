(ns configuration.core
  (:require [config.core :refer [env]])
  (:gen-class))

(defn -main
  [& args]
  (println (str "The db is " (:db env))))
