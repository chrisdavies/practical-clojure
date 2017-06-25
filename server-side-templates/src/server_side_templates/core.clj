(ns server-side-templates.core
  (:require [immutant.web :as web]
            [hiccup.core :as h])
  (:gen-class))


;; Chooses a random entry from the specified vector
(defn- rand-item [v]
  (->> (count v)
       rand
       int
       (nth v)))


;; Generates a random user name
(defn- user-description [n]
  (let [first-names ["Chris" "Callie" "Sally" "Joe" "Shmo"]
        last-names ["Davies" "Smith" "Ekeji" "Song" "Mayo"]]
    (str "User " n ": " (rand-item first-names) " " (rand-item last-names))))


;; Generates an li with a random user name in it
(defn- gen-user-li [n]
  [:li.user-item {:style "list-style-type: none;"} (user-description n)])


;; Generates a basic html document
(defn- gen-users [n]
  [:html
    [:head
      [:style "body {
          font-family: sans-serif;
          line-height: 1.4;
        }"]]
    [:body
      [:h1 (str n " Users")]
      [:ul.user-items (map gen-user-li (range 0 n))]]])


;; Serves up an html respons to incoming requests
(defn- app [request]
  {:status 200
   :body (h/html (gen-users 10))})


;; Runs immutant on port 8080
(defn -main []
  (web/run-dmc app {:host "localhost"
                    :port 8080
                    :path "/"}))
