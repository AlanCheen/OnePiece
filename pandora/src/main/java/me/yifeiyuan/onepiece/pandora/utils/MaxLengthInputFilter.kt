package me.yifeiyuan.onepiece.pandora.utils

import android.text.InputFilter
import android.text.Spanned

/**
 * Created by 程序亦非猿 on 2021/10/28.
 *
 * editText.setFilters(new InputFilter[] {new MaxLengthInputFilter(10)});
 *
 * editText.filters = arrayOf(MaxLengthInputFilter(10))
 */
class MaxLengthInputFilter(private var maxLength: Int) : InputFilter {

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {

        if (dest.length >= maxLength) {
            return ""
        }

        return null
    }
}