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
 * Created by 程序亦非猿 on 2022/7/12.
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

    fun register(
        intentFilter: IntentFilter
    ) {
        if (isLocalBroadcast) {
            LocalBroadcastManager.getInstance(context).registerReceiver(this, intentFilter)
        } else {
            context.registerReceiver(this, intentFilter)
        }
    }

    /**
     * TODO 需要？
     */
    fun send(intent: Intent) {
        if (isLocalBroadcast) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        } else {
            context.sendBroadcast(intent)
        }
    }

    /**
     * TODO 需要？
     */
    fun sendSync(intent: Intent) {
        if (isLocalBroadcast) {
            LocalBroadcastManager.getInstance(context).sendBroadcastSync(intent)
        } else {
            throw IllegalArgumentException()
        }
    }

    override fun onReceive(context: Context?, intent: Intent) {
        onReceive.invoke(intent)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.removeObserver(this)
        if (isLocalBroadcast) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
        }
    }
}