(ns configuration.core-test
  (:require [clojure.test :refer :all]
            [config.core :refer [env]]
            [configuration.core :refer :all]))

(deftest a-test
  (testing "Tests should be run with the test environment config"
    (is (= "test-db" (:db env)))))
