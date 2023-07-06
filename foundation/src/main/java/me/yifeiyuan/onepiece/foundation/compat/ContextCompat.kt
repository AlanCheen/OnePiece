package me.yifeiyuan.onepiece.foundation.compat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Build

/**
 * Created by 程序亦非猿 on 7/6/23.
 */

fun Context.registerReceiverCompat(receiver: BroadcastReceiver, filter: IntentFilter) {
    if (Build.VERSION.SDK_INT >= 33) {
        registerReceiver(receiver, filter, 0x2)
    } else {
        registerReceiver(receiver, filter)
    }
}
