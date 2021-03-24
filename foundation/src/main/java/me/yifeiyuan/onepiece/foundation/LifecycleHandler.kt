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
class LifecycleHandler : Handler, LifecycleObserver {

    private var lifecycleOwner: LifecycleOwner

    constructor(lifecycleOwner: LifecycleOwner) : super() {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(callback: Callback, lifecycleOwner: LifecycleOwner) : super(callback) {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(looper: Looper, lifecycleOwner: LifecycleOwner) : super(looper) {
        this.lifecycleOwner = lifecycleOwner
        addObserver()
    }

    constructor(looper: Looper, callback: Callback, lifecycleOwner: LifecycleOwner) : super(looper, callback) {
        this.lifecycleOwner = lifecycleOwner
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