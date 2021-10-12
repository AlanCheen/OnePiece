package me.yifeiyuan.onepiece.pandora

import android.os.Handler
import android.os.Looper

/**
 * Created by 程序亦非猿 on 2021/10/12.
 */

val mainThread = Handler(Looper.getMainLooper())

fun runOnMainThread(runnable: Runnable) {
    mainThread.post(runnable)
}