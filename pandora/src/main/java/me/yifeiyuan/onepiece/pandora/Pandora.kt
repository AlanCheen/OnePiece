package me.yifeiyuan.onepiece.pandora

import android.os.Handler
import android.os.Looper

/**
 * Created by 程序亦非猿 on 2021/9/13.
 */

fun <T> Any.runIfIs(clazz: Class<T>, block: T.() -> Unit) {
    if (clazz.isAssignableFrom(this::class.java)) {
        block(this as T)
    }
}