package me.yifeiyuan.onepiece.foundation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


/**
 * Created by 程序亦非猿 on 2021/3/24.
 */
open class LiveHandler @JvmOverloads constructor(
        private var lifecycleOwner: LifecycleOwner,
        looper: Looper = Looper.getMainLooper(),
        callback: Callback? = null
) : Handler(looper, callback), LifecycleObserver {

    init {
        addObserver()
    }

    private fun addObserver() {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}