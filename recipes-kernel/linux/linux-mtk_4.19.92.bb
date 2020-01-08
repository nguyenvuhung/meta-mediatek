DESCRIPTION = "Linux Kernel for Mediatek ARM SoCs (MT7623A/N)"
SECTION = "kernel"
LICENSE = "GPLv2"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
COMPATIBLE_MACHINE = "(mt7623-evb|mt7623-bpi-r2|mt7623-bpi-r2_customize|mt7623-bpi-r2_dev)"


LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

MEDIATEK_BSP_URL = "git://github.com/frank-w/BPI-R2-4.14.git"
BRANCH = "4.19-main"
SRCREV = "8d0d14f9a5269b3900fcb09e06e14ebb1adae09e"

SRC_URI = " \
	${MEDIATEK_BSP_URL};protocol=git;branch=${BRANCH} \
	"		
SRC_URI_append = " file://defconfig"

SRC_URI_append = " \
	file://defconfig-fragement-mt7623-bpi-r2.cfg \
	"

LINUX_VERSION ?= "4.19.92"
PV = "${LINUX_VERSION}"
PR = "r0"

S = "${WORKDIR}/git"


LINUX_VERSION_EXTENSION = "-mtk"


PROVIDES += " virtual/kernel"
DEPENDS += " lzop-native u-boot u-boot-mkimage-native"


# BPI-R2 requires uImage (no idea why zImage doesn't work...)
# Yocto's default uImage target does not append devicetree, so we strip the existing
# uImage header, add the dtb file and re-create the uImage header:
do_compile_append_mt7623-bpi-r2_dev () {
	if [ ! `${STAGING_BINDIR_NATIVE}/uboot-mkimage -l ${B}/arch/${ARCH}/boot/uImage | grep "Image Name" | grep "dtb"`]; then
		dd if=${B}/arch/${ARCH}/boot/uImage of=${B}/arch/${ARCH}/boot/zImage bs=64 skip=1
		cat ${B}/arch/${ARCH}/boot/zImage ${B}/arch/${ARCH}/boot/dts/${KERNEL_DEVICETREE} > ${B}/arch/${ARCH}/boot/zImage.dtb
		uboot-mkimage -A arm -O linux -T kernel -C none -a ${UBOOT_LOADADDRESS} -e ${UBOOT_ENTRYPOINT} -n "Linux-dtb-${PV}" \
			-d ${B}/arch/${ARCH}/boot/zImage.dtb ${B}/arch/${ARCH}/boot/uImage
		rm ${B}/arch/${ARCH}/boot/zImage.dtb
	fi
}



