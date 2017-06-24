(ns sending-emails.core
  (:require [sending-emails.mailer :as mailer])
  (:gen-class))


(defn send-test-email []
  (mailer/send-message
    {:from "bob@example.com"
     :to "bill@example.com"
     :subject "Hi, Bob!"
     :body [{:type "text/html"
             :content "<h1>This is a nifty email!</h1><p>Wouldn't you agree?</p>"}]}))


(defn -main
  "Send a test email like a boss"
  [& args]
  (send-test-email))
