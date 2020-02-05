SUMMARY = "The minimal image that can run Qt5 applications"
LICENSE = "MIT"

# Pulled from a mix of different images:
include recipes-core/images/core-image-mtk.bb
# This image is a little more full featured, and includes wifi
# support, provided you have a raspberrypi3

MY_APPQT = " \
    basicquick \
    qt5-opengles2-test \
"

MY_TOOLS = " \
    qtbase \
    qtbase-dev \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-tools \
"
MY_PKGS = " \
    qt3d \
    qt3d-dev \
    qt3d-mkspecs \
    qtcharts \
    qtcharts-dev \
    qtcharts-mkspecs \
    qtconnectivity-dev \
    qtconnectivity-mkspecs \
    qtquickcontrols2 \
    qtquickcontrols2-dev \
    qtquickcontrols2-mkspecs \
    qtdeclarative \
    qtdeclarative-dev \
    qtdeclarative-mkspecs \
    qtgraphicaleffects \
    qtgraphicaleffects-dev \
"
EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    curl \
    devmem2 \
    dosfstools \
    ethtool \
    findutils \
    grep \
    i2c-tools \
    ifupdown \
    iperf3 \
    iproute2 \
    netcat-openbsd \
    ntp \ 
    ntp-tickadj \
    procps \
    sysfsutils \
    tcpdump \
    tree \
    unzip \
    util-linux \
    util-linux-blkid \
    wget \
    xz \
    zip \
    sed \
    \
    bridge-utils \
    dnsmasq \
    conntrack-tools \
"

USB_TOOLS = " \
    libpcap libpcap-dev \
    libusb1 \
    python3-pyusb \
    usbutils usbutils-dev usbutils-python \
"
FEATURES_DEBUG = " \
    strace \
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
    mesa-demos \
"

#DISTRO_FEATURES_append += " bluez5 bluetooth wifi"
IMAGE_INSTALL_append = " \
    ${MY_APPQT} \
    ${MY_TOOLS} \
    ${MY_PKGS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${USB_TOOLS} \ 
    ${FEATURES_DEBUG} \
    ${FEATURES_MONITOR} \
    ${FEATURES_MQTT} \
    ${FEATURES_NET} \
    ${FEATURES_DHCP} \
    ${FEATURES_GRAPHIC} \
"
#CORE_IMAGE_EXTRA_INSTALL += "packagegroup-core-tools-testapps"