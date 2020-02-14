SUMMARY = "The minimal image that can run Qt5 applications"
LICENSE = "MIT"

# Pulled from a mix of different images:
include recipes-core/images/basic-dev-image.bb
# This image is a little more full featured, and includes wifi
# support, provided you have a raspberrypi3

#DISTRO_FEATURES_remove = "x11 wayland opengl"

MY_APPQT = " \
    basicquick \
    qtsmarthome \
    qcolorcheck \
    qshowfonts \
"

QT_TOOLS = " \
    qtbase \
    qtbase-dev \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-tools \
    qt5-env \
"
QT_PKGS = " \
"
FONTS = " \
    fontconfig \
    fontconfig-utils \
    ttf-bitstream-vera \
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
    boost \
"

USB_TOOLS = " \
    libpcap libpcap-dev \
    libusb1 \
    python3-pyusb \
    usbutils usbutils-dev usbutils-python \
"

FEATURES_MONITOR = " \
    watchdog \
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

FEATURES_GRAPHIC = " \
"

#DISTRO_FEATURES_append += " bluez5 bluetooth wifi"
IMAGE_INSTALL_append = " \
    ${MY_APPQT} \
    ${QT_TOOLS} \
    ${QT_PKGS} \
    ${FONTS} \
    ${TSLIB} \
    ${EXTRA_TOOLS_INSTALL} \
    ${USB_TOOLS} \ 
    ${FEATURES_MONITOR} \
    ${FEATURES_MQTT} \
    ${FEATURES_NET} \
    ${FEATURES_DHCP} \
    ${FEATURES_GRAPHIC} \
    packagegroup-qt5-toolchain-target \
    qcolorcheck-tools \
    tspress-tools \
"
#CORE_IMAGE_EXTRA_INSTALL += "packagegroup-core-tools-testapps"