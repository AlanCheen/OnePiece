package me.yifeiyuan.onepiece.pandora.ktx

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.*
import android.widget.TextView

/**
 *
 * CharSequence、String、Spannable 等字符串相关的扩展
 *
 * Created by 程序亦非猿 on 2021/8/31.
 */

fun CharSequence.toSpannableString(): SpannableString {
    return SpannableString(this)
}

fun CharSequence.toSpannableStringDSL(builder: SpannableString.() -> Unit): SpannableString {
    return SpannableString(this).apply(builder)
}

fun CharSequence.applyTo(textView: TextView) {
    textView.text = this
}

//已经有 ifEmpty 了
//fun CharSequence.ifEmpty(func: () -> CharSequence?): CharSequence? {
//    if (isEmpty()) {
//        return func()
//    }
//    return this
//}

fun SpannableString.strikeThrough(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StrikethroughSpan(), start, start + text.length, flag)
    return this
}

fun SpannableString.strikeThrough(
    targetText: String, flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(StrikethroughSpan(), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.strikeThrough(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StrikethroughSpan(), start, end, flag)
    return this
}

fun SpannableString.underLine(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(UnderlineSpan(), start, start + text.length, flag)
    return this
}

fun SpannableString.underLine(
    targetText: String, flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(UnderlineSpan(), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.underLine(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(UnderlineSpan(), start, end, flag)
    return this
}

fun SpannableString.boldItalic(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
    return this
}

fun SpannableString.boldItalic(
    targetText: String, flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(StyleSpan(Typeface.BOLD_ITALIC), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.boldItalic(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.ITALIC), start, end, flag)
    return this
}

fun SpannableString.italic(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
    return this
}

fun SpannableString.italic(
    targetText: String, flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(StyleSpan(Typeface.ITALIC), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.italic(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.ITALIC), start, end, flag)
    return this
}

fun SpannableString.bold(
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, start + text.length, flag)
    return this
}

fun SpannableString.bold(
    targetText: String, flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(StyleSpan(Typeface.BOLD), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.bold(
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(StyleSpan(Typeface.BOLD), start, end, flag)
    return this
}

fun SpannableString.backgroundColor(
    color: Int,
    targetText: String,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(BackgroundColorSpan(color), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.backgroundColor(
    color: Int,
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(BackgroundColorSpan(color), start, start + text.length, flag)
    return this
}

fun SpannableString.backgroundColor(
    color: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(BackgroundColorSpan(color), start, end, flag)
    return this
}

fun SpannableString.foregroundColor(
    color: Int,
    targetText: String,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    val index = indexOf(targetText, 0)
    if (index > 0) {
        setSpan(ForegroundColorSpan(color), index, index + targetText.length, flag)
    }
    return this
}

fun SpannableString.foregroundColor(
    color: Int,
    start: Int,
    text: CharSequence,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(ForegroundColorSpan(color), start, start + text.length, flag)
    return this
}

fun SpannableString.foregroundColor(
    color: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
): SpannableString {
    setSpan(ForegroundColorSpan(color), start, end, flag)
    return this
}


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

fun SpannableStringBuilder.applyTo(textView: TextView) {
    textView.text = this
}