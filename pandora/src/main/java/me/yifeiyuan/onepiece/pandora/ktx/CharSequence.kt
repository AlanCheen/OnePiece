package me.yifeiyuan.onepiece.pandora.ktx

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.widget.TextView

/**
 * Created by 程序亦非猿 on 2022/12/20.
 */

//已经有 ifEmpty 了
//fun CharSequence.ifEmpty(func: () -> CharSequence?): CharSequence? {
//    if (isEmpty()) {
//        return func()
//    }
//    return this
//}


fun CharSequence.toSpannableStringBuilder(builder: SpannableStringBuilder.() -> Unit): SpannableStringBuilder {
    return SpannableStringBuilder(this).apply(builder)
}

fun CharSequence.toSpannableString(): SpannableString {
    return SpannableString(this)
}

fun CharSequence.toSpannableStringDSL(builder: SpannableString.() -> Unit): SpannableString {
    return SpannableString(this).apply(builder)
}

fun CharSequence.applyTo(textView: TextView) {
    textView.text = this
}