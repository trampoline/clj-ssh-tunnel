(ns clj-ssh-tunnel.test.impl
  (:use clj-ssh-tunnel.impl
        midje.sweet))

(facts "about parse-ssh-destination"
  (parse-ssh-destination "foo@bar") => {:user "foo" :host "bar" :port 22}
  (parse-ssh-destination "foo@bar:1234") => {:user "foo" :host "bar" :port 1234})

(facts "about parse-rtunnel-spec"
  (parse-rtunnel-spec "1234:foo") => {:bind-address nil :port 1234 :host "foo" :hostport 1234}
  (parse-rtunnel-spec "1234:foo:4567") => {:bind-address nil :port 1234 :host "foo" :hostport 4567}
  (parse-rtunnel-spec "10.0.1.1:1234:foo:4567") => {:bind-address "10.0.1.1" :port 1234 :host "foo" :hostport 4567})