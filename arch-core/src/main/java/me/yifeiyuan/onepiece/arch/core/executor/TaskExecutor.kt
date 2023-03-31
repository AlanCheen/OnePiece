package me.yifeiyuan.onepiece.arch.core.executor

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull

/**
 * Created by 程序亦非猿 on 2023/3/31.
 */
object TaskExecutor {

    private val mainHandler = Handler(Looper.getMainLooper())

//    /**
//     * Executes the given task in the disk IO thread pool.
//     *
//     * @param runnable The runnable to run in the disk IO thread pool.
//     */
//    fun executeOnDiskIO(@NonNull runnable: Runnable){
//
//    }

    /**
     * Posts the given task to the main thread.
     *
     * @param runnable The runnable to run on the main thread.
     */
    fun postToMainThread(@NonNull runnable: Runnable){
        mainHandler.post(runnable)
    }

    /**
     * Executes the given task on the main thread.
     *
     *
     * If the current thread is a main thread, immediately runs the given runnable.
     *
     * @param runnable The runnable to run on the main thread.
     */
    fun executeOnMainThread(@NonNull runnable: Runnable) {
        if (isMainThread()) {
            runnable.run()
        } else {
            postToMainThread(runnable)
        }
    }

    fun executeOnMainThreadSafely(@NonNull runnable: Runnable) {
        try {
            if (isMainThread()) {
                runnable.run()
            } else {
                postToMainThread(runnable)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Returns true if the current thread is the main thread, false otherwise.
     *
     * @return true if we are on the main thread, false otherwise.
     */
    fun isMainThread(): Boolean = Thread.currentThread() == Looper.getMainLooper().thread
}