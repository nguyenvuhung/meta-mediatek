SUMMARY = "Adds an SSH server and swupdate to core-image-minimal"

# get directory of this append file while in global context:
# required for eMMC image build to pick up scatter file and
# preloader file from this directory
MTK_IMAGE_FILEDIR_mt7623-evb := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2 := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2_dev := "${THISDIR}"

# base image, change if required:
require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "ssh-server-openssh package-management"

CORE_IMAGE_EXTRA_INSTALL += " \
    swupdate \
    swupdate-www \
    swupdate-tools \
    swupdate-tools-hawkbit \
    pciutils \
    "

# Kernel modules using set GPIO 34 to 1 for LTE module use 14 Pin header
IMAGE_INSTALL_append = " \
    kernel-module-lte-trigger \
"

# Create new directory for rootfs
IMAGE_INSTALL_append += " createnewdirectory"

# Add toughscreen module
IMAGE_INSTALL_append += " kernel-module-dwav-usb-mt"

# Add app quectel_cm for start 4G module EC25E
IMAGE_INSTALL_append += " quectel-cm"

# unmask networking because yocto default mask networking the first
IMAGE_INSTALL_append += " systemd-systemctl"

# Enable NAT forwarding on wwan0
IMAGE_INSTALL_append += " nat-forwarding-ipv4"

