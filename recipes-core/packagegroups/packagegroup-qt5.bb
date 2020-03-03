DESCRIPTION = "Packagegroup for qt5 image."
MAINTAINER = "Diego Sueiro <diego.sueiro@embarcados.com.br>"

LICENSE = "MIT"

inherit packagegroup

PACKAGES += "\
            packagegroup-qt5-base \
            packagegroup-qt5-fonts \
            packagegroup-qt5-graphics \
            packagegroup-qt5-extra \
            packagegroup-qt5-extend \
            "

RDEPENDS_packagegroup-qt5-base = "\
            qtbase \
            qtbase-tools \
            qtbase-plugins \
            "

RDEPENDS_packagegroup-qt5-fonts = "\
            fontconfig \
            fontconfig-utils \
            ttf-bitstream-vera \
            liberation-fonts \
            "
RDEPENDS_packagegroup-qt5-graphics ="\
            qt3d \
            qt3d-qmlplugins \
            qt3d-tools \
            qtdeclarative \
            qtdeclarative-plugins \
            qtdeclarative-qmlplugins \
            qtdeclarative-tools \
            qtgraphicaleffects-qmlplugins \
            qtimageformats-plugins \
            qtmultimedia \
            qtmultimedia-plugins \
            qtmultimedia-qmlplugins \
            qtsvg \
            qtsvg-plugins \
            "            

RDEPENDS_packagegroup-qt5-extra ="\
			qttools \
			qttools-plugins \
			qttools-tools \
            qtconnectivity \
            qtconnectivity-qmlplugins \
            qtenginio \
            qtenginio-qmlplugins \
            qtlocation \
            qtlocation-plugins \
            qtlocation-qmlplugins \
            qtscript \
            qtsensors \
            qtsensors-plugins \
            qtsensors-qmlplugins \
            qtserialport \
            qtsystems \
            qtsystems-qmlplugins \
            qtsystems-tools \
            qtwebsockets \
            qtwebsockets-qmlplugins \
            qtxmlpatterns \
            qtxmlpatterns-tools \
            qtquickcontrols \
            qtquickcontrols2 \
            qtquickcontrols-qmlplugins \
            qtdatavis3d \
            qtgraphicaleffects \
            qtimageformats \
            "

# Requires Ruby to work
USE_RUBY = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebkit-dev', '', d)} \
"

# Requires Wayland to work
USE_WAYLAND = " \
    qtwayland-dev \
    qtwayland-mkspecs \
    qtwayland-plugins \
    qtwayland-tools \
"

# Requires X11 to work
USE_X11 = " \
    qtx11extras-dev \
    qtx11extras-mkspecs \
"

RDEPENDS_packagegroup-qt5-extend = " \
    libsqlite3-dev \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-dev', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-mkspecs', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-qmlplugins', '', d)} \
    qtbase-dev \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-staticdev \
    qtbase-tools \
    qttranslations-qtbase \
    qttranslations-qthelp \
    qtcharts-dev \
    qtcharts-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtcharts-qmlplugins', '', d)} \
    qtconnectivity-dev \
    qtconnectivity-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtconnectivity-qmlplugins', '', d)} \
    qttranslations-qtconnectivity \
    qtdeclarative-dev \
    qtdeclarative-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdeclarative-qmlplugins', '', d)} \
    qtdeclarative-tools \
    qtdeclarative-staticdev \
    qttranslations-qtdeclarative \
    qtenginio-dev \
    qtenginio-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtenginio-qmlplugins', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtgraphicaleffects-qmlplugins', '', d)} \
    qtimageformats-dev \
    qtimageformats-plugins \
    qtlocation-dev \
    qtlocation-mkspecs \
    qtlocation-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtlocation-qmlplugins', '', d)} \
    qttranslations-qtlocation \
    qtmqtt-dev \
    qtmqtt-mkspecs \
    qtmultimedia-dev \
    qtmultimedia-mkspecs \
    qtmultimedia-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtmultimedia-qmlplugins', '', d)} \
    qttranslations-qtmultimedia \
    qtscript-dev \
    qtscript-mkspecs \
    qttranslations-qtscript \
    qtsensors-dev \
    qtsensors-mkspecs \
    qtsensors-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtsensors-qmlplugins', '', d)} \
    qtserialport-dev \
    qtserialport-mkspecs \
    qtserialbus-dev \
    qtserialbus-mkspecs \
    qtsvg-dev \
    qtsvg-mkspecs \
    qtsvg-plugins \
    qtsystems-dev \
    qtsystems-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtsystems-qmlplugins', '', d)} \
    qttools-dev \
    qttools-mkspecs \
    qttools-staticdev \
    qttools-tools \
    ${USE_RUBY} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${USE_X11}', '', d)} \
    qtwebsockets-dev \
    qtwebsockets-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebsockets-qmlplugins', '', d)} \
    qttranslations-qtwebsockets \
    qtwebchannel-dev \
    qtwebchannel-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebchannel-qmlplugins', '', d)} \
    qtxmlpatterns-dev \
    qtxmlpatterns-mkspecs \
    qttranslations-qtxmlpatterns \
    qtquickcontrols2 \
    qtquickcontrols2-dev \
    qtquickcontrols2-mkspecs \
    qtvirtualkeyboard \
"

