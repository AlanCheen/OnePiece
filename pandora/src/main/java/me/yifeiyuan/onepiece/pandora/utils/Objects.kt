package me.yifeiyuan.onepiece.pandora.utils

/**
 * Created by 程序亦非猿 on 2022/12/5.
 */


fun allNotNull(vararg objects: Any?, func: () -> Unit) {
    var allNotNull = true
    for (o in objects) {
        o?.let {
            allNotNull = false
        }
    }
    if (allNotNull) {
        func.invoke()
    }
}

fun allNull(vararg objects: Any?, func: () -> Unit) {

}

fun anyNotNull(vararg objects: Any?, func: () -> Unit) {

}

fun anyNull(vararg objects: Any?, func: () -> Unit) {

}

fun firstNonNull(vararg objects: Any?) {

}

/**
 * this ?: foo  好像不需要 defaultIfNull
 */
fun <T : Any?> T.defaultIfNull(default: T): T {
    return this ?: default
}