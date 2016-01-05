(defproject mema "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.HTML"}

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-doo "0.1.4"]
            [lein-auto "0.1.2"]]

  :profiles {:dev
             {:dependencies [[com.cemerick/piggieback "0.2.1"]
                             [org.clojure/tools.nrepl "0.2.12"]]
              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]]

  :source-paths ["src" "test"]

  :clean-targets ^{:protect false} [:target-path "resources/public/js/" "out"]

  :doo {:build "test"
        :paths {:slimer "./node_modules/.bin/slimerjs"}
        :alias {:browsers [:chrome :firefox]
                :all [:browsers :headless]}}

  :cljsbuild
  { :builds {:test {:source-paths ["src" "test"]
                    :compiler     {:output-to     "out/testable.js"
                                   :main          'mema.runner
                                   :optimizations :simple}}}
   }
  )
