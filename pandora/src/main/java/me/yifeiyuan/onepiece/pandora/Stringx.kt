package me.yifeiyuan.onepiece.pandora

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan

/**
 * Created by 程序亦非猿 on 2021/8/31.
 *
 * KTX for CharSequence、String、SpannableString
 */

fun SpannableString.setBoldItalicStyle(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
    return this
}

fun SpannableString.setBoldItalicStyle(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.ITALIC), start, end, flag)
    return this
}

fun SpannableString.setItalicStyle(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
    return this
}

fun SpannableString.setItalicStyle(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.ITALIC), start, end, flag)
    return this
}

fun SpannableString.setBoldStyle(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
    return this
}

fun SpannableString.setBoldStyle(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, end, flag)
    return this
}

fun SpannableString.setForegroundColor(
    color: Int,
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(ForegroundColorSpan(color), start, start + text.length, flag)
    return this
}

fun SpannableString.setForegroundColor(
    color: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(ForegroundColorSpan(color), start, end, flag)
    return this
}

fun CharSequence.toSpannable(): SpannableString {
    return SpannableString(this)
}