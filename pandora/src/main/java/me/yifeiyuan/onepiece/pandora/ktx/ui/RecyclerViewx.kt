package me.yifeiyuan.onepiece.pandora.ktx.ui

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.yifeiyuan.onepiece.pandora.ktx.ifIs

/**
 *
 * 关于 RecyclerView 的扩展
 *
 *
 * Created by 程序亦非猿 on 2021/9/13.
 */

/**
 * 滚动到顶部
 */
fun RecyclerView.scrollToTop() {
    scrollToPositionWithOffset(0, 0)
}

fun RecyclerView.scrollToPositionWithOffset(position: Int, offset: Int = 0) {
    layoutManager?.ifIs<LinearLayoutManager>() {
        scrollToPositionWithOffset(position, offset)
    }

    layoutManager?.ifIs<GridLayoutManager>() {
        scrollToPositionWithOffset(position, offset)
    }
}

fun <T : RecyclerView.SimpleOnItemTouchListener> T.attachTo(recyclerView: RecyclerView): T {
    recyclerView.addOnItemTouchListener(this)
    return this
}

fun <T : RecyclerView.LayoutManager> T.attachTo(recyclerView: RecyclerView): T {
    recyclerView.layoutManager = this
    return this
}

fun <T : RecyclerView.Adapter<*>> T.attachTo(recyclerView: RecyclerView): T {
    recyclerView.adapter = this
    return this
}

/**
 * @param block
 * @see doOnScrollStateChanged
 */
fun RecyclerView.doOnScrolled(block: (recyclerView: RecyclerView, dx: Int, dy: Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            block(recyclerView, dx, dy)
        }
    })
}

/**
 * @param block
 * @see doOnScrolled
 */
fun RecyclerView.doOnScrollStateChanged(block: (recyclerView: RecyclerView, newState: Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            block(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }
    })
}