package me.yifeiyuan.onepiece.architecture.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.IllegalArgumentException

/**
 * 一个不需要手动反注册的 BroadcastReceiver
 *
 * @doc https://www.yuque.com/cxyfy/blog/xgddyq#cWXdI
 *
 * @see LiveHandler 一个不会内存泄露的 Handler
 *
 * Created by 程序亦非猿 on 2022/7/12.
 * @since v1.0.2.1
 */
class LiveBroadcastReceiver(
    private var context: Context,
    lifecycleOwner: LifecycleOwner,
    private var isLocalBroadcast: Boolean = true,
    private var onReceive: (intent: Intent) -> Unit
) : BroadcastReceiver(), LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onReceive(context: Context?, intent: Intent) {
        onReceive.invoke(intent)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.removeObserver(this)
        if (isLocalBroadcast) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
        }else{
            context.unregisterReceiver(this)
        }
    }
}