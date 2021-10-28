package me.yifeiyuan.onepiece.pandora.ktx

import android.text.SpannableString
import android.widget.TextView

/**
 * Created by 程序亦非猿 on 2021/8/31.
 *
 * KTX for CharSequence
 */

fun CharSequence.toSpannable(): SpannableString {
    return SpannableString(this)
}

fun CharSequence.toSpannableDSL(builder: SpannableString.() -> Unit): SpannableString {
    return SpannableString(this).apply(builder)
}

fun CharSequence.bindTo(textView: TextView) {
    textView.text = this
}