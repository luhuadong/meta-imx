# Build scenarios

The following are build setup scenarios for various configurations.
Set up the manifest and populate the Yocto Project layer sources with these commands:

```
$ mkdir imx-yocto-bsp
$ cd imx-yocto-bsp
$ repo init -u git@192.168.30.11:K37X/meta-imx.git
-b manifest  -m imx-5.4.47-2.2.0.xml
$ repo sync
```

## 修改部分

1. 当前的manifest里面，主要是修改了meta-imx为本地仓库

## meta-imx layer

该layer主要是将u-boot-imx修改为本地的仓库。