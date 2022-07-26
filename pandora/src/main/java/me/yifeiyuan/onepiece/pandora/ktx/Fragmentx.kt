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
    if (activity == null) {
        return false
    }
    return !activity!!.isFinishing && !activity!!.isDestroyed && isAdded
}

fun Fragment?.showToast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null || text == null || this.activity == null) {
        return
    }
    Toast.makeText(activity, text, duration).show()
}

fun Fragment?.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null || this.activity == null) {
        return
    }
    Toast.makeText(activity, resId, duration).show()
}
