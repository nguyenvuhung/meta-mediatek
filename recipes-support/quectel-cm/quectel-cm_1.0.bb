DESCRIPTION = "The connect manager is provided by Quectel to enable LTE network"
SECTION = "quectel-cm"
LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PN = "quectel-cm"
PR = "r0"

inherit update-rc.d systemd

QUECTEL_CM_URL = "git://github.com/nguyenvuhung/quectel-CM.git"
BRANCH = "master"
SRCREV = "${AUTOREV}"

SRC_URI = " \
	${QUECTEL_CM_URL};protocol=git;branch=${BRANCH} \
	"

SRC_URI_append = " \
	file://lte.service \
	file://lte_enable \
  file://lte.rules \
	"

S = "${WORKDIR}/git"
PV = "1.0"

EXTRA_OEMAKE += "'CC=${CC}' 'AR=${AR}' 'BUILDDIR=${S}'"
INSANE_SKIP_${PN} = "ldflags"

do_compile () {
    # Compile application
    cd ${S}/
    unset LDFLAGS
    oe_runmake all
}

do_install () {
    # create the /usr/bin folder in the rootfs give it default permissions
    install -d ${D}${bindir}
    # move quectel application to /usr/bin folder. in the rootfs.
    install -m 0755 ${S}/quectel-cm ${D}${bindir}

        # Configure for sysinit
#    install -d ${D}/${sysconfdir}/init.d
#    install -m 755 ${WORKDIR}/lte_enable ${D}${sysconfdir}/init.d


    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/lte.service ${D}${systemd_unitdir}/system

  if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/lte.rules ${D}${sysconfdir}/udev/rules.d/
  fi
}

FILES_${PN} += "${bindir}/quectel-cm"

INITSCRIPT_NAME = "lte_enable"
INITSCRIPT_PARAMS = "defaults 80"

SYSTEMD_SERVICE_${PN} = "lte.service"
