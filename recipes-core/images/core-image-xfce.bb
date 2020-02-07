SUMMARY = "Adds an SSH server and swupdate to core-image-minimal"

# get directory of this append file while in global context:
# required for eMMC image build to pick up scatter file and
# preloader file from this directory
MTK_IMAGE_FILEDIR_mt7623-evb := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2 := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2_dev := "${THISDIR}"

# base image, change if required:
require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += " splash package-management x11-base ssh-server-dropbear hwcodecs tools-testapps"

CORE_IMAGE_EXTRA_INSTALL += " \
    swupdate \
    swupdate-www \
    pciutils \
    "

#IMAGE_INSTALL += " packagegroup-xfce-base packagegroup-xfce-extended"
DISTRO_FEATURES += " opengl X11"

# Kernel modules using set GPIO 34 to 1 for LTE module use 14 Pin header
IMAGE_INSTALL_append = " \
    kernel-module-lte-trigger \
"

