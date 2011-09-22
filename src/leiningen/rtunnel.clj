(ns leiningen.rtunnel
  (:use clj-ssh-tunnel.core))

(defn rtunnel
  "setup an ssh reverse-tunnel"
  [project & args]
  (tunnel args))