package me.yifeiyuan.onepiece.pandora.ktx

import android.os.Handler
import android.os.Looper

/**
 * Handler
 *
 * Created by 程序亦非猿 on 2021/10/12.
 */

val mainThreadHandler = Handler(Looper.getMainLooper())

fun runOnMainThread(runnable: Runnable) {
    if (isMainThread()) {
        runnable.run()
    } else {
        mainThreadHandler.post(runnable)
    }
}

fun runOnMainThreadDelayed(delay: Long, runnable: Runnable) {
    mainThreadHandler.postDelayed(runnable, delay)
}

fun removeMainThreadRunnable(runnable: Runnable) {
    mainThreadHandler.removeCallbacks(runnable)
}
