package me.yifeiyuan.onepiece.pandora.ktx

import android.os.Handler
import android.os.Looper

/**
 * Handler 扩展
 *
 * Created by 程序亦非猿 on 2021/10/12.
 */

internal val mainThreadHandler = Handler(Looper.getMainLooper())

fun postToMainThread(runnable: Runnable) {
    if (isMainThread()) {
        runnable.run()
    } else {
        mainThreadHandler.post(runnable)
    }
}

fun postToMainThreadDelayed(delay: Long, runnable: Runnable) {
    mainThreadHandler.postDelayed(runnable, delay)
}

fun removeMainThreadRunnable(runnable: Runnable) {
    mainThreadHandler.removeCallbacks(runnable)
}
