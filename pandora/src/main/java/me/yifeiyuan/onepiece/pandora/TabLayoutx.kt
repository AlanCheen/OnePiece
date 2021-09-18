package me.yifeiyuan.onepiece.pandora

import com.google.android.material.tabs.TabLayout

/**
 * Created by 程序亦非猿 on 2021/9/13.
 */

fun TabLayout.doOnTabSelected(func: (tab: TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            func(tab)
//            this@doOnTabSelected.selectedTabPosition
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