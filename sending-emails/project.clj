(defproject sending-emails "0.1.0-SNAPSHOT"
  :description "A demo of how to send emails via Clojure"
  :url "https://github.com/chrisdavies/practical-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [yogthos/config "0.8"]
                 [com.draines/postal "2.0.2"]]
  :main ^:skip-aot sending-emails.core
  :target-path "target/%s"
  :profiles {:prod {:resource-paths ["config/prod"]}
             :dev  {:resource-paths ["config/dev"]}
             :test {:resource-paths ["config/test"]}
             :uberjar {:aot :all}})
