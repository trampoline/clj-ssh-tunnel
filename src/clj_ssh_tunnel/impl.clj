(ns clj-ssh-tunnel.impl
  (:require [clojure.tools.cli :as c]
            [clojure.contrib.strint :as strint]
            [clojure.contrib.str-utils :as stru])
  (:import (com.jcraft.jsch Session JSch Util Logger)))

(def logger
  (reify Logger
    (isEnabled [this _level] true)
    (log [this _level message] (println message))))

(defn parse-ssh-destination
  "parse an ssh destination: username@host[:port].
   returns a has with keys [:user :host :port]"
  [sshd]
  (let [[_ _ user host _ port] (re-matches #"((\w+)@)?([0-9a-zA-Z\.-]+)(:([0-9]+))?" sshd)]
    (if (or (not user) (not host)) 
      (throw (RuntimeException. (strint/<< "ssh destination [~{sshd}] should have form: username@host[:port]"))))
    {:user user :host host :port (if port (Integer/parseInt port) 22)}))

(defn parse-rtunnel-spec
  "parse an rtunnel spec: [bind-address:]port:host[:host-port]
   returns a hash with keys [:bind-address :port :host :host-port]"
  [rts]
  (let [[_ _ bind-address port host _ hostport] (re-matches #"(([0-9a-zA-Z\.-]+):)?([0-9]+):([0-9a-zA-Z\.-]+)(:([0-9]+))?" rts)]
    (if (not port) 
      (throw (RuntimeException. (strint/<< "rtunnel spec [~{rts}] should have form: [bind-address:]port[:host:[host-port]]"))))
    {:bind-address bind-address :port (Integer/parseInt port)
     :host host :hostport (or (if hostport (Integer/parseInt hostport)) (Integer/parseInt port))}))




