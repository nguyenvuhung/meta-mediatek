#!/bin/sh
iptables -t nat -F
iptables -t mangle -F
iptables -F
iptables -X
iptables --table nat --append POSTROUTING --out-interface wwan0 -j MASQUERADE
iptables --append FORWARD --in-interface lan2 -j ACCEPT