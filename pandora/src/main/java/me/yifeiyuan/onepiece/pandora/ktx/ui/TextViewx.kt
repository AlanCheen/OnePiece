package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Created by 程序亦非猿 on 2021/10/12.
 */

fun TextView.isTextNullOrEmpty(): Boolean {
    return text.isNullOrEmpty()
}

fun TextView.getTextOrEmpty(): CharSequence {
    return text ?: ""
}

fun <T : TextView> T.setTextColorRes(@ColorRes resId: Int): T {
    setTextColor(ContextCompat.getColor(context, resId))
    return this
}

fun <T : TextView> T.setTextRes(@StringRes resId: Int): T {
    text = context.resources.getString(resId)
    return this
}

fun <T : TextView> T.setTextColorByCondition(condition: Boolean, trueColor: Int, falseColor: Int) {
    val colorRes = if (condition) trueColor else falseColor
    setTextColor(ContextCompat.getColor(context, colorRes))
}