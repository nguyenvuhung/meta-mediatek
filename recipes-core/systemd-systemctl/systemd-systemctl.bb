DESCRIPTION = "Enable network service at the fisrt booting"
LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

inherit systemd

PN = "systemd-systemctl"
PR = "r0"

SRC_URI = " \
  file://network-unmask.service \
	"
S = "${WORKDIR}"
PV = "1.0"

do_install_append () {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/network-unmask.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "network-unmask.service "
