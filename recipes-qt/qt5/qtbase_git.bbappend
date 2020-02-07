PACKAGECONFIG_append = " accessibility eglfs fontconfig gles2 linuxfb tslib"

#replace opengl desktop with opengl es2
PACKAGECONFIG_GL = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
