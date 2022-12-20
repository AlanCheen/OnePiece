package me.yifeiyuan.onepiece.pandora.ktx

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView

/**
 * Created by 程序亦非猿 on 2022/12/20.
 *
 * Extensions for SpannableStringBuilder.
 */

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

fun SpannableStringBuilder.applyTo(textView: TextView) {
    textView.text = this
}