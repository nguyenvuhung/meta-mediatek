# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

SUMMARY = "OpenWrt Minimal Complete Image"

LICENSE = "MIT"

inherit core-image openwrt openwrt-kmods openwrt-services

MTK_IMAGE_FILEDIR_mt7623-bpi-r2_dev := "${THISDIR}"

# IMAGE_DEPENDS: list of Yocto images that contains a root filesystem
# it will be ensured they are built before creating swupdate image
IMAGE_DEPENDS = "core-image-mtk-swu"


CORE_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot \
    packagegroup-openwrt-minimal \
    \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
     '

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

IMAGE_FSTYPES += "ext4"
