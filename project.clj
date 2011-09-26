(defproject clj-ssh-tunnel "0.1.3"
  :description "a clojure ssh reverse-tunnel tool, using jsch"
  :url "https://github.com/trampoline/clj-ssh-tunnel"
  :main clj-ssh-tunnel.core
  :aot [clj-ssh-tunnel.core]
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [com.jcraft/jsch "0.1.44-1"]
                 [org.clojure/tools.cli "0.1.0"]]
  :dev-dependencies [[org.clojure/clojure "1.2.1"]
                     [com.jcraft/jsch "0.1.44-1"]
                     [org.clojure/tools.cli "0.1.0"]
                     [midje "1.3-alpha2"]
                     [lein-midje "1.0.3"]])