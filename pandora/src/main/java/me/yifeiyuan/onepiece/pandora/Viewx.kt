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

fun setViewsVisibility(visibility: Int, vararg views: View) {
    views.forEach {
        it.visibility = visibility
    }
}

fun setViewsGone(vararg views: View) {
//    views.forEach {
//        it.visibility = View.GONE
//    }
    setViewsVisibility(View.GONE, *views)
}

fun setViewsVisible(vararg views: View) {
//    views.forEach {
//        it.visibility = View.VISIBLE
//    }
    setViewsVisibility(View.VISIBLE, *views)
}

fun setViewsInvisible(vararg views: View) {
//    views.forEach {
//        it.visibility = View.INVISIBLE
//    }
    setViewsVisibility(View.INVISIBLE, *views)
}

fun <T : View> T.goneIf(bool: Boolean): T {
    this.visibility = if (!bool) View.VISIBLE else View.GONE
    return this
}

fun <T : View> T.visibleIf(visible: Boolean): T {
    this.visibility = if (visible) View.VISIBLE else View.GONE
    return this
}

fun <T : View> T.visible(): T {
    this.visibility = View.VISIBLE
    return this
}

fun <T : View> T.invisible(): T {
    this.visibility = View.INVISIBLE
    return this
}

fun <T : View> T.gone(): T {
    this.visibility = View.GONE
    return this
}

fun <T : View> T.enable(): T {
    this.isEnabled = true
    return this
}

fun <T : View> T.disable(): T {
    this.isEnabled = false
    return this
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
