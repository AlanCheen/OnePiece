package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.app.Activity
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Checkable

/**
 *
 * View & ViewGroup 扩展
 *
 * 扩展属性：
 * @see activity
 * @see isVisible
 *
 * 扩展方法：
 * @see visible
 * @see visibleIf
 * @see invisible
 * @see invisibleIf
 * @see gone
 * @see goneIf
 * @see enable
 *
 * Created by 程序亦非猿 on 2021/6/24.
 */

val View.activity
    get() = context as Activity

fun <T : Activity> View.asActivity() = context as T

/**
 * view.isVisible = true / false
 *
 * @see visible
 * @see visibleIf
 * @see invisible
 * @see invisibleIf
 * @see gone
 * @see goneIf
 */
var View.isVisible: Boolean
    set(value) {
        visibility = if (value) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    get() = visibility == View.VISIBLE


/**
 * 设置多个 View 的可见性
 * 
 * @param visibility 可见性
 * @param views views
 * @see setViewsGone
 * @see setViewsVisible
 * @see setViewsInvisible
 */
fun setViewsVisibility(visibility: Int, vararg views: View) {
    views.forEach {
        it.visibility = visibility
    }
}

/**
 * 多个 View 设置为 GONE
 * @param views views
 */
fun setViewsGone(vararg views: View) {
    views.forEach {
        it.visibility = View.GONE
    }
}

/**
 * 多个 View 设置为 VISIBLE
 * @param views views
 */
fun setViewsVisible(vararg views: View) {
    views.forEach {
        it.visibility = View.VISIBLE
    }
}

/**
 * 多个 View 设置为 INVISIBLE
 * @param views views
 */
fun setViewsInvisible(vararg views: View) {
    views.forEach {
        it.visibility = View.INVISIBLE
    }
}

/**
 * 如果符合 condition==true 就设置为 GONE
 */
fun <T : View> T.goneIf(condition: Boolean): T {
    this.visibility = if (condition) View.GONE else View.VISIBLE
    return this
}

/**
 * 如果符合 condition==true 就设置为 VISIBLE
 */
fun <T : View> T.visibleIf(condition: Boolean): T {
    this.visibility = if (condition) View.VISIBLE else View.GONE
    return this
}

/**
 * 如果符合 condition==true 就设置为 INVISIBLE
 */
fun <T : View> T.invisibleIf(condition: Boolean): T {
    this.visibility = if (condition) View.INVISIBLE else View.VISIBLE
    return this
}

/**
 * 设置可见性为 VISIBLE
 */
fun <T : View> T.visible(): T {
    this.visibility = View.VISIBLE
    return this
}

/**
 * 设置可见性为 INVISIBLE
 */
fun <T : View> T.invisible(): T {
    this.visibility = View.INVISIBLE
    return this
}

/**
 * 设置可见性为 GONE
 */
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

fun <T : View> T.selected(): T {
    isSelected = true
    return this
}

fun <T : View> T.unselected(): T {
    isSelected = false
    return this
}

/**
 * 设置为 勾选状态
 * CheckBox RadioButton 等实现了 Checkable
 */
fun <T : Checkable> T.checked(): T {
    isChecked = true
    return this
}

/**
 * 设置为 未勾选状态
 */
fun <T : Checkable> T.unchecked(): T {
    isChecked = false
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

/**
 * 设置节流点击
 *
 * @param time 节流阈值
 * @param onClick 点击响应
 */
fun <T : View> T.doOnThrottledClick(time: Long = 500, onClick: (v: T) -> Unit): T {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View) {
            val clickTime = SystemClock.elapsedRealtime()
            if (clickTime - lastClickTime < time) {
                return
            }
            lastClickTime = clickTime
            onClick(this@doOnThrottledClick)
        }
    })
    return this
}

fun ViewGroup.inflate(resId: Int): View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}

