package me.yifeiyuan.onepiece.pandora.ktx

import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by 程序亦非猿 on 2022/7/15.
 */


/**
 * 在一个新线程上运行
 * @param block
 */
fun <T> T.runOnNewThread(block: T.() -> Unit) {
    Thread {
        block()
    }.start()
}

/**
 * 在主线程上运行
 */
fun <T> T.runOnMainThread(block: T.() -> Unit) {
    postToMainThread {
        block()
    }
}

//fun <T> T.mainThread(block: T.() -> Unit) {
//    mainThreadHandler.post {
//        block.invoke(this)
//    }
//}

/**
 *
 * 为什么不用 Looper.myLooper() == Looper.getMainLooper()？ 因为 myLooper() 可能会创建 ThreadLocalMap 导致浪费，而用 Thread 判断不需要
 *
 * @return 是否是主线程
 */
fun isMainThread(): Boolean {
    return Looper.getMainLooper().thread == Thread.currentThread()
}

fun createSingleThreadExecutor(): Executor {
    return Executors.newSingleThreadExecutor()
}