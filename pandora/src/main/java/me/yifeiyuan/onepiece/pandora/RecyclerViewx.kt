package me.yifeiyuan.onepiece.pandora

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by 程序亦非猿 on 2021/9/13.
 */

fun RecyclerView.scrollToPositionWithOffset(position: Int, offset: Int) {
    layoutManager?.runIfIs(LinearLayoutManager::class.java) {
        scrollToPositionWithOffset(position, offset)
        return@runIfIs
    }

    layoutManager?.runIfIs(GridLayoutManager::class.java) {
        scrollToPositionWithOffset(position, offset)
        return@runIfIs
    }
}

fun <T : RecyclerView.LayoutManager> T.attachTo(recyclerView: RecyclerView): T {
    recyclerView.layoutManager = this
    return this
}

fun <T : RecyclerView.Adapter<*>> T.attachTo(recyclerView: RecyclerView): T {
    recyclerView.adapter = this
    return this
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

fun RecyclerView.onScrollStateChanged(func: (recyclerView: RecyclerView, newState: Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            func(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }
    })
}