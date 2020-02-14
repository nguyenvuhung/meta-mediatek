DESCRIPTION = "Linux Kernel for Mediatek ARM SoCs (MT7623A/N)"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM ?= "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

#require recipes-kernel/linux/linux-yocto.inc
inherit kernel

COMPATIBLE_MACHINE = "(mt7623-evb|mt7623-bpi-r2|mt7623-bpi-r2_customize|mt7623-bpi-r2_dev)"

MEDIATEK_BSP_URL = "git://github.com/frank-w/BPI-R2-4.14.git"
BRANCH = "4.14-main"
SRCREV = "b9dc9e30b91372a8beea7971aca6950198cc1fd7"

SRC_URI = " \
	${MEDIATEK_BSP_URL};protocol=git;branch=${BRANCH} \
	"

SRC_URI_append = " \
	file://defconfig \
	file://0001-WWAN-driver-Support-module-4G-EC25-of-quectel.patch \
	file://0001-Remove-indicate-error-when-warning.patch \
	"

LINUX_VERSION ?= "4.14.158"
PV = "${LINUX_VERSION}"
PR = "r0"

S = "${WORKDIR}/git"

LINUX_VERSION_EXTENSION = "-mtk"
#KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} defconfig"

PROVIDES += " virtual/kernel"
DEPENDS += " lzop-native u-boot u-boot-mkimage-native"

KCONF_BSP_AUDIT_LEVEL = "0"


# BPI-R2 requires uImage (no idea why zImage doesn't work...)
# Yocto's default uImage target does not append devicetree, so we strip the existing
# uImage header, add the dtb file and re-create the uImage header:
do_compile_append_mt7623-bpi-r2 () {
	if [ ! `${STAGING_BINDIR_NATIVE}/uboot-mkimage -l ${B}/arch/${ARCH}/boot/uImage | grep "Image Name" | grep "dtb"`]; then
		dd if=${B}/arch/${ARCH}/boot/uImage of=${B}/arch/${ARCH}/boot/zImage bs=64 skip=1
		cat ${B}/arch/${ARCH}/boot/zImage ${B}/arch/${ARCH}/boot/dts/${KERNEL_DEVICETREE} > ${B}/arch/${ARCH}/boot/zImage.dtb
		uboot-mkimage -A arm -O linux -T kernel -C none -a ${UBOOT_LOADADDRESS} -e ${UBOOT_ENTRYPOINT} -n "Linux-dtb-${PV}" \
			-d ${B}/arch/${ARCH}/boot/zImage.dtb ${B}/arch/${ARCH}/boot/uImage
		rm ${B}/arch/${ARCH}/boot/zImage.dtb
	fi
}



