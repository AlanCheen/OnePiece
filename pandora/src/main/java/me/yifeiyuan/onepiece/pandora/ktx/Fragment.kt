package me.yifeiyuan.onepiece.pandora.ktx

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Fragment 扩展
 *
 * Created by 程序亦非猿 on 2021/4/12.
 */

/**
 *
 * @return 是否是激活的状态
 */
fun Fragment.isActive(): Boolean {
    return isAdded && activity.isActive()
}

fun Fragment.isInactive(): Boolean = !isActive()

fun Fragment.showToast(text: CharSequence, isShort: Boolean = true) {
    if (text.isNotEmpty() && isActive()) {
        activity.showToast(text, isShort)
    }
}

fun Fragment.showToast(@StringRes resId: Int, isShort: Boolean = true) {
    if (isActive()) {
        activity.showToast(resId, isShort)
    }
}
