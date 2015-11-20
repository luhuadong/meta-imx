SUMMARY = "Freescale GPU SDK Samples"
DESCRIPTION = "Set of sample applications for Freescale GPU"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3880bb9c943b135a30fad5e8aabd3ee9"
DEPENDS = "${X11_DEPENDS} ${WL_DEPENDS} devil gstreamer1.0 gstreamer1.0-plugins-base"
DEPENDS_append_mx6q = " virtual/libgles2"
DEPENDS_append_mx6dl = " virtual/libgles2"
DEPENDS_append_mx6sx = " virtual/libgles2"

X11_DEPENDS = "${@base_contains('DISTRO_FEATURES', 'x11', 'xrandr', '', d)}"
WL_DEPENDS = "${@base_contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

inherit fsl-eula-unpack

# For backwards compatibility
RPROVIDES_${PN} = "vivante-gpu-sdk"
RREPLACES_${PN} = "vivante-gpu-sdk"
RCONFLICTS_${PN} = "vivante-gpu-sdk"

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"

SRC_URI[md5sum] = "c4f01354ae90d86773ee7e6c588ea0e4"
SRC_URI[sha256sum] = "2621d3897f944c97c2332f14b4085d6326a287889119b3f017bc0c79fa8d6da7"

BACKEND = "${@base_contains('DISTRO_FEATURES', 'wayland', 'Wayland', \
                base_contains('DISTRO_FEATURES', 'x11', 'X11', 'FB', d), d)}"

HAS_VPU = "1"
HAS_VPU_mx6sx = "0"

do_compile () {
    export FSL_GRAPHICS_SDK=${S}
    export FSL_PLATFORM_NAME=Yocto
    export ROOTFS=${STAGING_DIR_HOST}
    ./build.sh -f GNUmakefile_Yocto EGLBackend=${BACKEND}
}

do_install () {
    export FSL_GRAPHICS_SDK=${S}
    export FSL_PLATFORM_NAME=Yocto
    install -d "${D}/opt/${PN}"
    ./build.sh -f  GNUmakefile_Yocto EGLBackend=${BACKEND} install 
    cp -r bin/* "${D}/opt/${PN}"
    if [ "${HAS_VPU}" = "0" ]; then
        rm -rf ${D}/opt/${PN}/GLES2/DirectMultiSamplingVideoYUV
        rm -rf ${D}/opt/${PN}/GLES3/DirectMultiSamplingVideoYUV
    fi
    rm -rf ${D}/opt/${PN}/GLES2/S05_PrecompiledShader
    rm -rf ${D}/opt/${PN}/GLES3/S05_PrecompiledShader
}

FILES_${PN} += "/opt/${PN}"
FILES_${PN}-dbg += "/opt/${PN}/*/*/.debug /usr/src/debug"
INSANE_SKIP_${PN} += "already-stripped rpaths"

COMPATIBLE_MACHINE = "(mx6)"