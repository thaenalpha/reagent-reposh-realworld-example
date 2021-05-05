(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.core :as r]))

(defonce mock-articles
  [{:title "Backpackging is fun"}])

(defn articles
  [items]
  (if-not (seq items)
    [:div.article-preview "Loading..."]
    (if (= 0 (count items))
      [:div.article-preview "No articles are here... yet."]
      [:div
       (for [article items]
         [:h2 (:title article)])])))

(defn header
  []
  [:nav.navbar.navbar-light>div.container
   [:a.navbar-brand "conduit"]])

(defn banner
  [token]
  (when token
    [:div.banner>div.container
     [:h1.logo-front "conduit"]
     [:p "A place to share your knowledge."]]))

(defn main-view
  []
  [:div.col-md-9
   [:div.feed-toggle>ul.nav.nav-pills.outline-active>li.nav-item
    [:a.nav-link.active {:href ""} "Global Feed"]]
   [articles mock-articles]])

(defn home-page
  []
  [:div.home-page
   [banner "auth-user-token"]
   [:div.container.page>div.row
    [main-view]
    [:div.col-md-3
     [:div.sidebar
      [:p "Popular tags"]]]]])

(defn app
  []
  [:div
   [header]
   [home-page]])

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (r/render [app] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (render))
