package me.yifeiyuan.onepiece.pandora.ktx.ui

import com.google.android.material.tabs.TabLayout

/**
 * TabLayout 的扩展
 *
 * @see doOnTabReselected
 * @see doOnTabSelected
 * @see doOnTabUnselected
 *
 * Created by 程序亦非猿 on 2021/9/13.
 */

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

fun TabLayout.doOnTabUnselected(func: (tab: TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            func(tab)
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }
    })
}