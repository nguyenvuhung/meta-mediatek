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

USE_DEVFS = "1"

# This variable is triggered to check if sysvinit must be overwritten by a single rcS
export SYSVINIT = "no"

CORE_IMAGE_EXTRA_INSTALL += "base-files \
		base-passwd \
		busybox \
		mtd-utils \
		mtd-utils-ubifs \
		libconfig \
                ${@bb.utils.contains('SWUPDATE_INIT', 'tiny', 'initscripts-swupdate', 'initscripts sysvinit', d)} \
		util-linux-sfdisk \
		 "

IMAGE_LINGUAS = " "

fix_inittab_swupdate () {
	sed -e 's/1\:2345.*/1\:2345:respawn:\/bin\/sh/' \
		"${IMAGE_ROOTFS}${sysconfdir}/inittab" | \
		sed -e 's/^z6/#&/' | \
		 sed -e 's/.*getty.*//' \
		> "${IMAGE_ROOTFS}${sysconfdir}/inittab.swupdate"
	rm ${IMAGE_ROOTFS}${sysconfdir}/inittab
	mv ${IMAGE_ROOTFS}${sysconfdir}/inittab.swupdate ${IMAGE_ROOTFS}${sysconfdir}/inittab
}

ROOTFS_POSTPROCESS_COMMAND += "${@bb.utils.contains('SWUPDATE_INIT', 'tiny', 'fix_inittab_swupdate', '',  d)}" 
