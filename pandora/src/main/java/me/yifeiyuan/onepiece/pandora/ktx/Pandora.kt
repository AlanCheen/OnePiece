package me.yifeiyuan.onepiece.pandora.ktx

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

/**
 *
 * 为什么不用 Looper.myLooper() == Looper.getMainLooper()？ 因为 myLooper() 可能会创建 ThreadLocalMap 导致浪费，而用 Thread 判断不需要
 */
fun isMainThread() :Boolean{
    return Looper.getMainLooper().thread == Thread.currentThread()
}
