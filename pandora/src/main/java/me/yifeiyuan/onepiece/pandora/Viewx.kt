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

fun EditText.doAfterTextChanged(func: (s: Editable, string: String, length: Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            func(s, s.toString(), s.length)
        }

    })
}

fun ViewPager.doOnPageSelected(func: (position: Int) -> Unit) {

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(p0: Int) {
            func(p0)
        }

        override fun onPageScrollStateChanged(p0: Int) {
        }
    })
}

fun RecyclerView.doOnScrolled(func: (recyclerView: RecyclerView, dx: Int, dy: Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            func(recyclerView, dx, dy)
        }
    })
}

fun TabLayout.doOnTabReselected(func: (tab: TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
            func(tab)
        }
    })
}

fun TabLayout.doOnTabSelected(func: (tab: TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            func(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }
    })
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
            func(v as T)
        }
    })
}
