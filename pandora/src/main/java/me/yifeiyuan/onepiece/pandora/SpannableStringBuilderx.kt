package me.yifeiyuan.onepiece.pandora

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView

/**
 * Created by 程序亦非猿 on 2021/9/13.
 *
 * 方法跟 SpannableString 其实类似，所以似乎并不需要它。
 */

fun CharSequence.toSpannableStringBuilder(builder: SpannableStringBuilder.() -> Unit): SpannableStringBuilder {
    return SpannableStringBuilder(this).apply(builder)
}

fun SpannableStringBuilder.bold(
    start: Int, end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) {
    setSpan(StyleSpan(Typeface.BOLD), start, end, flag)
}

fun SpannableStringBuilder.bold(
    start: Int, text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
}

fun SpannableStringBuilder.foregroundColor(
    color: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) {
    setSpan(ForegroundColorSpan(color), start, end, flag)
}

fun SpannableStringBuilder.foregroundColor(
    color: Int,
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) {
    setSpan(ForegroundColorSpan(color), start, start + text.length, flag)
}

fun SpannableStringBuilder.bindTo(textView: TextView) {
    textView.text = this
}