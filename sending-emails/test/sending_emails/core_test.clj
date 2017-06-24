(ns sending-emails.core-test
  (:require [clojure.test :refer :all]
            [config.core :refer [env]]
            [sending-emails.core :as mailer]))

(deftest stores-emails-in-mem
  (testing "Sends a test email"
    (with-redefs [sending-emails.mailer/send-message identity]
      (is (= "Hi, Bob!" (:subject (mailer/send-test-email)))))))
