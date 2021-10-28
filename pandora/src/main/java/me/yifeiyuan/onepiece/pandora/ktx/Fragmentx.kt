package me.yifeiyuan.onepiece.pandora.ktx

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Created by 程序亦非猿 on 2021/4/12.
 */

fun Fragment?.toast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null || text == null || this.activity == null) {
        return
    }
    Toast.makeText(activity, text, duration).show()
}

fun Fragment?.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null || this.activity == null) {
        return
    }
    Toast.makeText(activity, resId, duration).show()
}
