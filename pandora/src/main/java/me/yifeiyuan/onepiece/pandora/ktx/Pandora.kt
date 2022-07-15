package me.yifeiyuan.onepiece.pandora.ktx

import android.os.Looper

/**
 * Created by 程序亦非猿 on 2021/9/13.
 */

/**
 * 在 try 块中执行，会 catch 异常并打印
 */
fun <T> T.runWithTryCatch(block: T.() -> Unit): T {
    try {
        block()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this
}

/**
 *
 * 为什么不用 Looper.myLooper() == Looper.getMainLooper()？ 因为 myLooper() 可能会创建 ThreadLocalMap 导致浪费，而用 Thread 判断不需要
 *
 * @return 是否是主线程
 */
fun isMainThread(): Boolean {
    return Looper.getMainLooper().thread == Thread.currentThread()
}

fun <T> T.runOnMainThread(block: T.() -> Unit) {
    mainThreadHandler.post {
        block()
    }
}

/**
 * 如果是 T 类型，就执行 block
 *
 * @see runAs
 */
inline fun <reified T> Any.ifIs(block: T.() -> Unit) {
    // this.javaClass.isAssignableFrom(T::class.java)
    if (this is T) {
        block(this as T)
    }
}

/**
 *
 * 如果是 R 类型，则以 R 类型为参数执行 block
 * （不想写强转代码）
 *
 * @see ifIs
 */
inline fun <reified R> Any.runAs(block: (R) -> Unit) {
    if (this is R) {
        block(this)
    }
}