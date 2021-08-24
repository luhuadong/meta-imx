# Copyright (C) 2021 Harmonye
# Copyright 2003-2021 Bocon Automation Technology Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "K37X Firmware image to validate i.MX8 machines. \
This image contains base used to Bocon K37X devices without GUI and applications."
LICENSE = "MIT"

#inherit core-image
require recipes-core/images/core-image-minimal.bb

## Select Image Features
IMAGE_FEATURES += " \
    debug-tweaks \
    package-management \
    nfs-server \
    ssh-server-dropbear \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
"

IMAGE_INSTALL += " \
    fsl-rc-local \
    git \
    dosfstools \
    mmc-utils \
"
