package me.yifeiyuan.onepiece.pandora

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan

/**
 * Created by 程序亦非猿 on 2021/8/31.
 */

fun SpannableString.setForegroundColorSpan(
    color: Int,
    start: Int,
    end: Int,
    flags: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(ForegroundColorSpan(color), start, end, flags)
    return this
}

fun SpannableString.setRelativeSizeSpan(
    proportion: Float,
    start: Int,
    end: Int,
    flags: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(RelativeSizeSpan(proportion), start, end, flags)
    return this
}

fun CharSequence.toSpannableString(): SpannableString {
    return SpannableString(this)
}