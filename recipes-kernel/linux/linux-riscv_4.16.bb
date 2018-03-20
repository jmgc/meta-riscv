DESCRIPTION = "RISC-V Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "\
    git://github.com/riscv/riscv-linux.git;branch=${BRANCH} \
    file://earlyprintk.cfg \
"

LINUX_VERSION ?= "4.16-rc5"
LINUX_VERSION_EXTENSION_append = "-riscv"

BRANCH = "riscv-all"
SRCREV = "7f82cffaad50273eedb654b58167c662383eac99"

PV = "${LINUX_VERSION}+git${SRCPV}"

DEPENDS_append = " libgcc"

KBUILD_DEFCONFIG_qemuriscv64 = "defconfig"
KCONFIG_MODE="--alldefconfig"

KERNEL_CC_append = " ${TOOLCHAIN_OPTIONS} ${SECURITY_NOPIE_CFLAGS}"
KERNEL_LD_append = " -no-pie"

COMPATIBLE_MACHINE = "(qemuriscv64)"

KERNEL_FEATURES_remove = "features/debug/printk.scc"