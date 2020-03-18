SUMMARY = "XXX project directory structure"
# FIXME - add proper license below
LICENSE = "CLOSED"
PV = "1.0"

S = "${WORKDIR}"

inherit allarch

SRC_URI_append = "\
                file://WMT_SOC.cfg \
                file://ROMv2_lm_patch_1_0_hdr.bin \
                file://ROMv2_lm_patch_1_1_hdr.bin \
                file://WIFI_RAM_CODE_7623 \
                file://WIFI \
                "

do_install () {
    # Experiment - create folder store firmware WIFI
    install -d ${D}/system/etc/firmware
    install -d ${D}/etc/firmware
    install -d ${D}/etc/firmware/nvram

    install -m 644 ${WORKDIR}/WMT_SOC.cfg ${D}/system/etc/firmware/
    install -m 644 ${WORKDIR}/ROMv2_lm_patch_1_0_hdr.bin ${D}/etc/firmware/
    install -m 644 ${WORKDIR}/ROMv2_lm_patch_1_1_hdr.bin ${D}/etc/firmware/
    install -m 644 ${WORKDIR}/WIFI_RAM_CODE_7623 ${D}/etc/firmware/
    install -m 644 ${WORKDIR}/WIFI ${D}/etc/firmware/nvram/
}

FILES_${PN} = " \
    /system/etc/firmware    \
    /etc/firmware   \
    /etc/firmware/nvram \
"