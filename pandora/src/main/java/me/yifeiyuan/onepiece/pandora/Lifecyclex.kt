package me.yifeiyuan.onepiece.pandora

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Created by 程序亦非猿 on 2021/8/25.
 */

fun <T, L : LiveData<T>> LifecycleOwner.observe(liveData: L, func: (T?) -> Unit) {
    liveData.observe(this) { func(it) }
}

fun <T, L : LiveData<T>> LifecycleOwner.observeNonNull(liveData: L, func: (T) -> Unit) {
    liveData.observe(this) {
        it?.let(func)
    }
}