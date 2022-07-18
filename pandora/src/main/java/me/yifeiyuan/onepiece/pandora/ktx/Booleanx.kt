package me.yifeiyuan.onepiece.pandora.ktx

/**
 * Boolean
 *
 * Created by 程序亦非猿 on 2022/7/18.
 */

/**
 * 如果为 true 则执行
 */
fun Boolean.ifTrue(block: () -> Unit) {
    if (this) {
        block()
    }
}

/**
 * 如果为 false 则执行
 */
fun Boolean.ifFalse(block: () -> Unit) {
    if (!this) {
        block()
    }
}