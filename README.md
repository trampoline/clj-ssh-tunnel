## clj-ssh-tunnel ##

sets up a reverse ssh tunnel, useful for running on a cloud service to securely tunnel access 
to resources otherwise hidden behind the cloud firewall

## Usage ##

add a dependency or a dev-dependency to your project.clj

    [clj-ssh-tunnel "0.1.0"]

if you added a dev-dependency, run with :

    lein rtunnel -d user@host.com -r 1234:foo:4567 -k /home/foo/.ssh/id_rsa -pp keypassword --verbose?

if you added a regular dependency, run with :

    lein run -m clj-ssh-tunnel.core -d user@host.com -r 1234:foo:4567 -k /home/foo/.ssh/id_rsa -pp keypassword --verbose?

both these invocations will login as 'user' to 'host.com', authenticating with the ssh private key in '/home/foo/.ssh/id_rsa' 
which is protected with the password 'keypassword'. once logged in an ssh reverse tunnel will be setup from port 1234 (on host.com)
to foo:4567. a bind address for the reverse tunnel can also be specified using the form :

    -r 192.168.1.50:1234:foo:4567

## License ##

Copyright (C) 2011 Trampoline Systems Ltd, daniel kwiecinski and mccraigmccraig

Distributed under the Eclipse Public License, the same as Clojure.
