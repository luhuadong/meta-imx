# README

该仓库主要有以下三个分支：
1. master : 说明文档
2. zeus-5.4.47-2.2.0 : 对应原yocto中的meta-imx
3. manifest : 放置该版本的manifest的xml文件


## 1. 初始化仓库

参考**i.MX_Yocto_Project_User's_Guide.pdf**文档，配置好repo。
配置完repo后，执行以下指令：

```shell
$ mkdir imx-yocto-bsp
$ cd imx-yocto-bsp
$ repo init -u git@192.168.30.11:K37X/meta-imx.git -b manifest  -m imx-5.4.47-2.2.0.xml
$ repo sync
```

## 修改部分

1. 当前的manifest里面，主要是修改了meta-imx为本地仓库

## meta-imx layer

该layer主要是将u-boot-imx修改为本地的仓库。


## 2. 构建

### 构建根文件系统

```shell
bitbake imx-image-k37x
```

### 构建工具链

```shell
bitbake imx-image-k37x -c populate_sdk
```

