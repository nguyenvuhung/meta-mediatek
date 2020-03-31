DESCRIPTION = "utilities to enable WIFI module"
SECTION = "wmt_loader"
LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI_append = " \
	file://Makefile \
  file://wmt_loader.c \
	"

S = "${WORKDIR}"
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
    # move wmt_loader application to /usr/bin folder. in the rootfs.
    install -m 0755 ${S}/wmt_loader ${D}${bindir}
}

FILES_${PN} += "${bindir}/wmt_loader"
