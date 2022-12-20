package me.yifeiyuan.onepiece.pandora.ktx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 *
 * Extensions for FragmentActivity.
 *
 * Created by 程序亦非猿 on 2022/12/20.
 */

/**
 * 移除所有的 Fragment
 */
fun FragmentActivity.clearFragments() {
    val listFragment: List<Fragment> = supportFragmentManager.fragments
    for (fragment in listFragment) {
        supportFragmentManager.beginTransaction().remove(fragment)
            .commitAllowingStateLoss()
    }
}