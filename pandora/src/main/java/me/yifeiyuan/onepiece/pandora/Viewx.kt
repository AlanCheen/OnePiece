package me.yifeiyuan.onepiece.pandora

import android.os.SystemClock
import android.view.View

/**
 * Created by 程序亦非猿 on 2021/6/24.
 */

fun View.wrapWithDebouncedClick(listener: View.OnClickListener, debounceTime: Long = 500) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = SystemClock.elapsedRealtime()
            if (clickTime - lastClickTime < debounceTime) {
                return
            }
            lastClickTime = clickTime
            listener.onClick(v)
        }
    })
}

fun View.doOnDebouncedClick(debounceTime: Long = 500, func: (v: View?) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = SystemClock.elapsedRealtime()
            if (clickTime - lastClickTime < debounceTime) {
                return
            }
            lastClickTime = clickTime
            func(v)
        }
    })
}
