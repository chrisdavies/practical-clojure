(ns sending-emails.mailer
  (:require [postal.core :as postal]
            [config.core :refer [env]])
  (:gen-class))

;; Launches an email in a web browser rather than sending it... This could
;; and maybe should generate a new temporary file per email, but for now,
;; we'll just reuse the one file.
(defn- launch-email [cmd content]
  (let [rt (Runtime/getRuntime)
        file-name "./target/email.html"]
    (spit file-name
          (-> (:body content)
              first
              (:content)
              (str "<footer style=\"border-top: 1px solid #AAA; margin-top: 24px; padding: 24px; opacity: 0.75; line-height: 1.4\">"
                   "<label style=\"display: block\">Email metadata</label>"
                   (assoc content :body (rest (:body content)))
                   "</footer>")))
    (.exec rt (str cmd " " file-name))))


(defn send-message
  "Sends an email using the smtp settings specified in config.edn.
  content is a map that looks something like this:
    (send {:from \"me@draines.com\"
           :to \"foo@example.com\"
           :subject \"Hi!\"
           :body [{:type \"text/html\"
                   :content \"<b>Test!</b>\"}})"
  [content]
  (let [opts (:mailer env)]
    (case (:type opts)
      :smtp          (postal/send-message opts content)
      :letter-opener (launch-email (:cmd opts) content)
      :none          nil)))
