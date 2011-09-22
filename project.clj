(defproject clj-ssh-tunnel "0.1.2"
  :description "a clojure ssh reverse-tunnel tool, using jsch"
  :url "https://github.com/trampoline/clj-ssh-tunnel"
  :main clj-ssh-tunnel.core
  :aot [clj-ssh-tunnel.core]
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.jcraft/jsch "0.1.44-1"]
                 [org.clojure/tools.cli "0.1.0"]]
  :dev-dependencies [[org.clojure/clojure "1.2.1"]
                     [org.clojure/clojure-contrib "1.2.0"]
                     [com.jcraft/jsch "0.1.44-1"]
                     [org.clojure/tools.cli "0.1.0"]
                     [swank-clojure "1.4.0-SNAPSHOT"]
                     [midje "1.2.0"]
                     [lein-midje "1.0.3"]])