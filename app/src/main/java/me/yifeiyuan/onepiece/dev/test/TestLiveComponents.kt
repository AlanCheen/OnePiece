package me.yifeiyuan.onepiece.dev.test

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import me.yifeiyuan.onepiece.arch.core.CoreActivity
import me.yifeiyuan.onepiece.arch.core.LifecycleAwareBroadcastReceiver
import me.yifeiyuan.onepiece.arch.core.LifecycleAwareHandler
import me.yifeiyuan.onepiece.dev.R
import me.yifeiyuan.onepiece.pandora.ktx.registerLocalBroadcast
import me.yifeiyuan.onepiece.pandora.ktx.sendLocalBroadcast
import me.yifeiyuan.onepiece.pandora.ktx.showToast

private const val TAG = "TestLiveComponents"

/**
 * Created by 程序亦非猿 on 2022/7/25.
 */
class TestLiveComponents() : CoreActivity(R.layout.activity_test_live_components) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LifecycleAwareHandler(lifecycleOwner = this).postDelayed({
            Log.d(TAG, "onCreate: 我不会内存泄露~")
        }, 30000)

        val handler = LifecycleAwareHandler(lifecycleOwner = this) { message: Message ->
            Log.d(TAG, "onCreate() called with: message = $message")
        }

        val message = Message().apply {
            what = 666
        }
        handler.sendMessage(message)

        val receiver =
            LifecycleAwareBroadcastReceiver(context = this, lifecycleOwner = this, isLocalBroadcast = true) {
                Log.d(TAG, "onCreate() called $it")

                showToast("收到广播")
            }

        val intentFilter = IntentFilter().apply {
            addAction("b")
            addCategory("a")
        }

        registerLocalBroadcast(receiver, intentFilter)
    }

    fun testSendLocalBroadcast(view: View) {
        sendLocalBroadcast(Intent("b").apply {
            addCategory("a")
        })
    }

}