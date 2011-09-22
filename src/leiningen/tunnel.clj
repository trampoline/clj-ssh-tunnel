(ns leiningen.tunnel
  (:require clj-ssh-tunnel.core))

(defn tunnel
  "setup an ssh reverse-tunnel"
  [project & args]
  (clj-ssh-tunnel.core/tunnel args))