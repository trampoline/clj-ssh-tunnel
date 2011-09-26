(ns clj-ssh-tunnel.core
  "create an ssh rtunnel"
  (:use clj-ssh-tunnel.impl)
  (:require [clojure.tools.cli :as c]
            [clojure.string :as str])
  (:import (com.jcraft.jsch Session JSch Util Logger))
  (:gen-class))

(defn create-ssl-tunnel [{:keys [dest rtunnel private-key-path passphrase verbose]}]
  "setup an ssh reverse-tunnel"
  (do
    (and verbose (JSch/setLogger logger))
    (let [jsch (JSch.)
          ssh-dest (parse-ssh-destination dest)
          rts (parse-rtunnel-spec rtunnel)]
      (if verbose
        (do
          (println (format "ssh destination: %s@%s:%s" (:user ssh-dest) (:host ssh-dest) (:port ssh-dest)))
          (println (format "rtunnel: %s:%s:%s:%s" (:bind-address rts) (:port rts) (:host rts) (:hostport rts)))))
      (.addIdentity jsch private-key-path (.getBytes passphrase "UTF-8"))
      (let [session (.getSession jsch (:user ssh-dest) (:host ssh-dest) (:port ssh-dest))]
        (doto session
          (.setConfig "StrictHostKeyChecking" "no")
          (.setDaemonThread false)
          .connect
          (.setPortForwardingR (:bind-address rts) (:port rts) (:host rts) (or (:hostport rts) (:port rts)))))
      (println (format "tunnel created with endpoint: %s" (str/join ":" (filter identity [(:bind-address rts) (:host ssh-dest) (:port rts)]))))
      (flush)
      (while true (Thread/sleep 1000)))))

(defn tunnel
  "setup an ssh reverse-tunnel"
  [args]
  (let [opts
        (c/cli
          args
          (c/required ["-d" "--dest" "ssh destination: username@host[:port]"])
          (c/required ["-r" "--rtunnel" "rtunnel spec: [bind-address:]port:host[:host-port]"])
          (c/optional ["-k" "--private-key-path" "Specify path to the private key" :default ".ssh/id_rsa"])
          (c/optional ["-pp" "--passphrase" "private key passphrase" :default ""])
          (c/optional ["-v" "--verbose"]))]

    (create-ssl-tunnel opts)))

(defn -main 
  "setup an ssh reverse-tunnel"
  [& args]
  (tunnel args))