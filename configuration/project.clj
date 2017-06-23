(defproject configuration "0.1.0-SNAPSHOT"
  :description "A demo of how to configure Clojure applications"
  :url "https://github.com/chrisdavies/practical-clojure"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [yogthos/config "0.8"]]
  :main ^:skip-aot configuration.core
  :target-path "target/%s"
  :profiles {:prod {:resource-paths ["config/prod"]}
             :dev  {:resource-paths ["config/dev"]}
             :test {:resource-paths ["config/test"]}
             :uberjar {:aot :all}})
