(defproject server-side-templates "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]
                 [ring/ring-devel "1.6.1"]
                 [org.immutant/web "2.1.9"]]
  :main ^:skip-aot server-side-templates.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
