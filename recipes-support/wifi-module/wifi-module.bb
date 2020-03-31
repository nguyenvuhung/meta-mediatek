DESCRIPTION = "Enable WIFI module"
SECTION = "WIFI module"
LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


inherit systemd

SRC_URI_append = " \
	file://wifi_enable.service \
	file://wifi_enable.sh \
	"

do_install () {
    # create the /usr/bin folder in the rootfs give it default permissions
    install -d ${D}${bindir}
    # move wifi_enable.sh application to /usr/bin folder. in the rootfs.
    install -m 0755 ${WORKDIR}/wifi_enable.sh ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/wifi_enable.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "wifi_enable.service"