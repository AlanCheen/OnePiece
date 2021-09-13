package me.yifeiyuan.onepiece.pandora

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by 程序亦非猿 on 2021/9/13.
 */

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