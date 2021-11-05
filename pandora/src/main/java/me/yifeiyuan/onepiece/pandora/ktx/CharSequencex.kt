package me.yifeiyuan.onepiece.pandora.ktx

import android.text.SpannableString
import android.widget.TextView

/**
 * Created by 程序亦非猿 on 2021/8/31.
 *
 * KTX for CharSequence
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