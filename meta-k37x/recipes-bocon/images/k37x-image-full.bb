# Copyright (C) 2021 Harmonye
# Copyright 2003-2021 Bocon Automation Technology Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "K37X Image to validate i.MX8 machines. \
This image contains everything used to Bocon K37X devices including GUI and applications."
LICENSE = "MIT"

#inherit core-image
require recipes-core/images/core-image-minimal.bb

# The root's password is "k37x"
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -P k37x root;"
#EXTRA_USERS_PARAMS = "useradd -P k37x bocon;"

inherit populate_sdk_qt5

CONFLICT_DISTRO_FEATURES = "directfb"

# Add Chinese fonts
IMAGE_LINGUAS = "en-us zh-cn"
FONT_CHINESE = "ttf-droid-sans ttf-droid-sans-fallback ttf-droid-sans-mono ttf-droid-serif freetype"

## Select Image Features
IMAGE_FEATURES += " \
    debug-tweaks \
    package-management \
    splash \
    nfs-server \
    ssh-server-dropbear \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
                                                       '', d), d)} \
"
ERPC_COMPS ?= ""
ERPC_COMPS_append_mx7ulp = "packagegroup-imx-erpc"

ISP_PKGS = ""
ISP_PKGS_mx8mp = "packagegroup-imx-isp"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-tools-bluetooth \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
    ${ERPC_COMPS} \
    ${ISP_PKGS} \
"

IMAGE_INSTALL += " \
    fsl-rc-local \
    usbutils \
    pciutils \
    zeromq \
    hiredis \
    rapidjson \
    util-linux \
    paho-mqtt-c \
    protobuf \
    zlog \
    curl \
    sqlite3 \
    mariadb \
    libev \
    wvdial \
    spitools \
    libgpiod \
    qtvirtualkeyboard \
    packagegroup-qt5-webengine \
    packagegroup-qt5-imx \
    docker connman connman-client \
    ${FONT_CHINESE} \
"
