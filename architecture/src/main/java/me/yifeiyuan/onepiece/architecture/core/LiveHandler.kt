package me.yifeiyuan.onepiece.architecture.core

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


/**
 *
 * 一个不会内存泄露的 Handler
 *
 * @param lifecycleOwner 绑定生命周期，ON_DESTROY 的时候自动解绑
 *
 * @see LiveBroadcastReceiver
 *
 * Created by 程序亦非猿 on 2021/3/24.
 */
open class LiveHandler @JvmOverloads constructor(
    lifecycleOwner: LifecycleOwner,
    looper: Looper = Looper.getMainLooper(),
    callback: Callback? = null
) : Handler(looper, callback), LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(lifecycleOwner: LifecycleOwner) {
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}