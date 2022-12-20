package me.yifeiyuan.onepiece.pandora.ktx

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.widget.TextView

/**
 *
 * CharSequence、String、Spannable 等字符串相关的扩展
 *
 * Created by 程序亦非猿 on 2021/8/31.
 */

/**
 * Convert String to Color.
 *
 * 将 String 转换成 Color
 *
 * <pre>
 *     val color = "#00ff00".toColor()
 * </pre>
 */
fun String?.toColor(): Int {
    return Color.parseColor(this)
}

/**
 *
 * <pre>
 *     val color = "#00ff00".toColor("#FFFFFF")
 * </pre>
 */
fun String?.toColorOr(defaultColor: String): Int {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        Color.parseColor(defaultColor)
    }
}

/**
 *
 * <pre>
 *     val color = "#00ff00".toColor(Color.TRANSPARENT)
 * </pre>
 */
fun String?.toColorOr(defaultColor: Int): Int {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        defaultColor
    }
}