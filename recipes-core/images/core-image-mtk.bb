SUMMARY = "Adds an SSH server and swupdate to core-image-minimal"

# get directory of this append file while in global context:
# required for eMMC image build to pick up scatter file and
# preloader file from this directory
MTK_IMAGE_FILEDIR_mt7623-evb := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2 := "${THISDIR}"
MTK_IMAGE_FILEDIR_mt7623-bpi-r2_dev := "${THISDIR}"

# base image, change if required:
require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "ssh-server-openssh"

CORE_IMAGE_EXTRA_INSTALL += " \
    swupdate \
    swupdate-www \
    pciutils \
    "

# Add some nescesary package into rootfs for feature SWupdate operate

CORE_IMAGE_EXTRA_INSTALL += "\
		mtd-utils \
		mtd-utils-ubifs \
		libconfig \
		util-linux-sfdisk \
		 "
