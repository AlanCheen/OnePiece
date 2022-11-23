package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * TextView 的扩展
 *
 * @see isTextNullOrEmpty
 * @see setTextColorRes
 * @see setTextRes
 * @see getTextOrEmpty
 *
 * Created by 程序亦非猿 on 2021/10/12.
 */

/**
 * 文本是否为空
 */
fun TextView.isTextNullOrEmpty(): Boolean {
    return text.isNullOrEmpty()
}

/**
 * 获取文本，默认空字符串
 */
fun TextView.getTextOrEmpty(): CharSequence {
    return text ?: ""
}

/**
 * 设置颜色值资源
 * @param resId
 */
fun <T : TextView> T.setTextColorRes(@ColorRes resId: Int): T = apply {
    setTextColor(ContextCompat.getColor(context, resId))
}

/**
 * 设置文本资源
 * @param resId
 */
fun <T : TextView> T.setTextRes(@StringRes resId: Int): T = apply {
    text = context.resources.getString(resId)
}

/**
 * 根据条件设置颜色值
 * @param condition
 * @param trueColor
 * @param falseColor
 */
fun <T : TextView> T.setTextColorByCondition(
    condition: Boolean,
    trueColor: Int,
    falseColor: Int
): T = apply {
    val colorRes = if (condition) trueColor else falseColor
    setTextColor(ContextCompat.getColor(context, colorRes))
}

fun <T : TextView> T.strike(): T = apply {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun <T : TextView> T.underLine(): T = apply {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun <T : TextView> T.antiAlias(): T = apply {
    paintFlags = paintFlags or Paint.ANTI_ALIAS_FLAG
}

fun <T : TextView> T.bold(): T = apply {
    paintFlags = paintFlags or Paint.FAKE_BOLD_TEXT_FLAG
}

