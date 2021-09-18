package me.yifeiyuan.onepiece.pandora

import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * Created by 程序亦非猿 on 2021/6/24.
 */

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.wrapWithThrottledClick(listener: View.OnClickListener, time: Long = 500) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = SystemClock.elapsedRealtime()
            if (clickTime - lastClickTime < time) {
                return
            }
            lastClickTime = clickTime
            listener.onClick(v)
        }
    })
}

fun View.shareOnThrottledClick(
    vararg views: View,
    time: Long = 500,
    func: (v: View) -> Unit
) {
    val listener = object : View.OnClickListener {
        var lastClickTime: Long = 0

        override fun onClick(v: View?) {
            val clickTime = SystemClock.elapsedRealtime()
            if (clickTime - lastClickTime < time) {
                return
            }
            lastClickTime = clickTime
            func(v!!)
        }
    }

    for (v in views) {
        v.setOnClickListener(listener)
    }
}

fun <T : View> T.doOnThrottledClick(time: Long = 500, func: (v: T) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View) {
            val clickTime = SystemClock.elapsedRealtime()
            if (clickTime - lastClickTime < time) {
                return
            }
            lastClickTime = clickTime
            func(this@doOnThrottledClick)
        }
    })
}
