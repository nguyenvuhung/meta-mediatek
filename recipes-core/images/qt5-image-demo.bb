SUMMARY = "A Qt5 image with all examples and demos from meta-qt5"

LICENSE = "MIT"

include recipes-core/images/core-image-mtk.bb

MY_APPQT = " \
    basicquick \
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

DEV_SDK = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    elfutils elfutils-binutils \
    file \
    gcc \
    gcc-symlinks \
    g++ \
    g++-symlinks \
    gdb \
    gettext \
    git \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    ltrace \
    make \
    perl-modules \
    pkgconfig \
    python3-modules \
    strace \
    \
    python-setuptools \
    python-dateutil \
    python-pip \
    openssh-sftp \
    openssh-sftp-server \
    openssh openssh-keygen \
    rsync \
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

VNG_PACKAGE = " \
    boost \
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

#DISTRO_FEATURES_append += " bluez5 bluetooth wifi"
IMAGE_INSTALL_append = " \
    ${MY_APPQT} \
	${MY_TOOLS} \
    ${TSLIB} \
    ${DEV_SDK} \
    ${EXTRA_TOOLS_INSTALL} \
    ${USB_TOOLS} \ 
    ${FEATURES_DEBUG} \
    ${FEATURES_MONITOR} \
    ${FEATURES_MQTT} \
    ${FEATURES_NET} \
    ${FEATURES_DHCP} \
    ${FEATURES_GRAPHIC} \
    ${VNG_PACKAGE} \
	${QT5_App} \
"
#CORE_IMAGE_EXTRA_INSTALL += "packagegroup-core-tools-testapps"
