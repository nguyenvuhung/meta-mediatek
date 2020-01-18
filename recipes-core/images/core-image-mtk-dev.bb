SUMMARY = "The image for bananapi r2"

# get directory of this append file while in global context:
# required for eMMC image build to pick up scatter file and
# preloader file from this directory
MTK_IMAGE_FILEDIR_mt7623-evb := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2 := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2_dev := "${THISDIR}"

# base image, change if required:
require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = " \
    packagegroup-vng \
    kernel-modules \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

CORE_IMAGE_EXTRA_INSTALL += " \
    pciutils \
    quectel-cm \
    "

# Kernel modules using set GPIO 34 to 1 for LTE module use 14 Pin header
#IMAGE_INSTALL_append = " \
#    kernel-module-lte-trigger \
#"

