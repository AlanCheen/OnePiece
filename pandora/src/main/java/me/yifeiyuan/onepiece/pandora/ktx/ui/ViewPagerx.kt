package me.yifeiyuan.onepiece.pandora.ktx.ui

import androidx.viewpager.widget.ViewPager

/**
 * ViewPager
 *
 * Created by 程序亦非猿 on 2021/9/13.
 *
 */

fun ViewPager.doOnPageScrolled(
    func: (
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) -> Unit
) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            func(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    })
}

fun ViewPager.doOnPageSelected(func: (position: Int) -> Unit) {

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            func(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    })
}

fun ViewPager.doOnPageScrollStateChanged(func: (state: Int) -> Unit) {

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
            func(state)
        }
    })
}