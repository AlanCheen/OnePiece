package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * EditText 的扩展
 *
 * @see doOnTextChanged
 * @see doAfterTextChanged
 * @see doBeforeTextChanged
 *
 * Created by 程序亦非猿 on 2021/9/13.
 *
 */

fun EditText.doOnTextChanged(func: (s: CharSequence, start: Int, before: Int, count: Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            func(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {
        }
    })
}

fun EditText.doBeforeTextChanged(func: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            func(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
        }
    })
}

fun EditText.doAfterTextChanged(func: (text: String, length: Int, isEmpty: Boolean, s: Editable) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            func(s.toString(), s.length, s.isEmpty(), s)
        }
    })
}