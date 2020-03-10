#require qt5.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "qtbase"

SRCREV = "66f65fc21d128bcb8ef02c02474863d2ad92acac"
SRC_URI = "git://github.com/emqtt/qmqtt.git"
           

SRC_URI_append = " \
    file://0001-Remove-compile-gtest.patch \
" 

S = "${WORKDIR}/git"

require recipes-qt/qt5/qt5.inc
#inherit qmake5
