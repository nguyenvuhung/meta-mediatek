SUMMARY = "A console development image with some C/C++ dev tools"

# get directory of this append file while in global context:
# required for eMMC image build to pick up scatter file and
# preloader file from this directory
MTK_IMAGE_FILEDIR_mt7623-evb := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2 := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2_dev := "${THISDIR}"

# base image, change if required:
require recipes-core/images/core-image-minimal.bb

#IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"

inherit image

CORE_IMAGE_EXTRA_INSTALL += " \
    swupdate \
    swupdate-www \
    pciutils \
    "

CORE_OS = " \
    openssh openssh-keygen openssh-sftp-server \
    packagegroup-core-boot \
    tzdata \
"

KERNEL_EXTRA_INSTALL = " \
"

WIREGUARD = " \
"

WIFI_SUPPORT = " \
    crda \
    iw \
    linux-firmware-ath9k \
    linux-firmware-ralink \
    linux-firmware-rtl8192ce \
    linux-firmware-rtl8192cu \
    linux-firmware-rtl8192su \
    linux-firmware-wl18xx \
    wpa-supplicant \
"

DEV_SDK_INSTALL = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    elfutils elfutils-binutils \
    file \
    g++ \
    g++-symlinks \
    gcc \
    gcc-symlinks \
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
    python-modules \
    python3-modules \
    strace \
"

DEV_EXTRAS = " \
"

EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    curl \
    devmem2 \
    dosfstools \
    e2fsprogs-mke2fs \
    ethtool \
    findutils \
    grep \
    i2c-tools \
    ifupdown \
    iperf3 \
    iproute2 \
    iptables \
    less \
    lsof \
    netcat-openbsd \
    ntp ntp-tickadj \
    parted \
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
"

VNG_IOT = " \
    libmosquitto1 \
    libmosquittopp1 \
    mosquitto \
    mosquitto-dev \
    mosquitto-clients \
    python-paho-mqtt \
"

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${KERNEL_EXTRA_INSTALL} \
    ${WIFI_SUPPORT} \
    ${WIREGUARD} \
    ${VNG_IOT} \
"

#set_local_timezone() {
#    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
#}

#disable_bootlogd() {
#    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
#}

#ROOTFS_POSTPROCESS_COMMAND += " \
#    set_local_timezone ; \
#"

export IMAGE_BASENAME = "console-image-mtk"

