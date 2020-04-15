SUMMARY = "A Qt5 image with all examples and demos from meta-qt5"

LICENSE = "MIT"

include recipes-core/images/core-image-mtk.bb

MY_APPQT = " \
    qt5-opengles2-test \
    qtsmarthome \
    cinematicexperience \
    qt5everywheredemo \
    qcolorcheck \
    qshowfonts \
    tspress \
"
MY_TOOLS = " \
    qt5-env \
"

TSLIB = " \
    tslib \
    tslib-calibrate \
    tslib-conf \
"

EXTRA_TOOLS_INSTALL = " \
    bridge-utils \
    dnsmasq \
    conntrack-tools \
"

FEATURES_MQTT = " \
    libmosquitto1 \
    libmosquittopp1 \
    mosquitto \
    mosquitto-dev \
    mosquitto-clients \
    python-paho-mqtt \
"

FEATURES_NET = " \
    iptables \
    lldpd \
    igmpproxy \
"

FEATURES_DHCP = " \
    dhcp-server \ 
    dhcp-relay \
    libfile-slurp-perl \
    libhtml-parser-perl \
    net-snmp \
"

QT5_App = " \
			packagegroup-qt5-base \
            packagegroup-qt5-fonts \
            packagegroup-qt5-graphics \
            packagegroup-qt5-extra \
			packagegroup-qt5-extend \
			cinematicexperience \
			qt5everywheredemo \
			qt5ledscreen \
			qt5nmapcarousedemo \
			qt5nmapper \
			qtsmarthome \
     		quitbattery \
  		  	quitindicators \
			qsiv \
			qt5-demo-extrafiles \
			"

IMAGE_INSTALL_append = " \
    ${MY_APPQT} \
	${MY_TOOLS} \
    ${TSLIB} \
    ${EXTRA_TOOLS_INSTALL} \
    ${FEATURES_MQTT} \
    ${FEATURES_NET} \
    ${FEATURES_DHCP} \
    ${QT5_App} \
"
