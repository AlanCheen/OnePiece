package me.yifeiyuan.onepiece.arch.core

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


/**
 *
 * 一个不会内存泄露的 Handler
 *
 * @doc https://www.yuque.com/cxyfy/blog/xgddyq#LAqhG
 *
 * @param lifecycleOwner 绑定生命周期，ON_DESTROY 的时候自动解绑
 * @see LifecycleAwareBroadcastReceiver
 *
 * Created by 程序亦非猿 on 2021/3/24.
 * @since v1.0.2.1
 */
class LifecycleAwareHandler @JvmOverloads constructor(
    lifecycleOwner: LifecycleOwner,
    looper: Looper = Looper.getMainLooper(),
    callback: Callback? = null,
    var onHandleMessage: ((Message) -> Unit)? = null
) : Handler(looper, callback), LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        onHandleMessage?.invoke(msg)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(lifecycleOwner: LifecycleOwner) {
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}