FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://bindlan1.network \
    file://bindlan2.network \
    file://br0.netdev \
"

FILES_${PN} += " \
    ${sysconfdir}/systemd/network/bindlan1.network \
    ${sysconfdir}/systemd/network/bindlan2.network \
    ${sysconfdir}/systemd/network/br0.netdev \
"

do_install_append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/bindlan1.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/bindlan2.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/br0.netdev ${D}${sysconfdir}/systemd/network
}