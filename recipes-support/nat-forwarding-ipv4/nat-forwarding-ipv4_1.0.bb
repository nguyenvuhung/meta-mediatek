DESCRIPTION = "NAT forwarding"
SECTION = "NAT forwarding"
LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit systemd

SRC_URI_append = " \
	file://nat.service \
	file://nat-forwarding-ipv4.sh \
	"

do_install () {
    # create the /usr/bin folder in the rootfs give it default permissions
    install -d ${D}${bindir}
    # move quectel application to /usr/bin folder. in the rootfs.
    install -m 0755 ${WORKDIR}/nat-forwarding-ipv4.sh ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/nat.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "nat.service"