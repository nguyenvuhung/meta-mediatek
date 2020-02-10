DESCRIPTION = "D-WAV USB(HID) Multi-Touch(800x480) driver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${MODULE_NAME}:"

inherit module

DEPENDS = "linux-mtk"
PN = "kernel-module-dwav-usb-mt"
PR = "r0"

#REQUIRED_DISTRO_FEATURES = "lte-trigger"

SRC_URI_append = "\
                file://dwav-usb-mt.c \
                file://Makefile \
                "

S = "${WORKDIR}/dwav-usb-mt"
PV = "1.0.0"

MODULE_NAME = "dwav-usb-mt"
# Kernel module packages MUST begin with 'kernel-module-', otherwise
# multilib image generation can fail.
#
# The following line is only necessary if the recipe name does not begin
# with kernel-module-.
RPROVIDES_${PN} += "kernel-module-${MODULE_NAME}"

do_compile() {
    # Build kernel module
    cp ${S}/../${MODULE_NAME}.c ${S}
    cp ${S}/../Makefile ${S}
    cd ${S}/
    make -C ${KBUILD_OUTPUT} M=${S} modules
}

do_install () {
    # Create destination directory
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/extra/
    install -d ${STAGING_KERNEL_DIR}/include


    # Install kernel module
    install -m 644 ${B}/${MODULE_NAME}.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/extra/

    # Install shared library to STAGING_KERNEL_DIR for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/Module.symvers ${STAGING_KERNEL_DIR}/include/${MODULE_NAME}.symvers

}


FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/${MODULE_NAME}.ko \
"

# Autoload dwav-usb-mt module
KERNEL_MODULE_AUTOLOAD = " dwav-usb-mt"
