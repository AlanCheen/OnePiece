package me.yifeiyuan.onepiece.pandora

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Created by 程序亦非猿 on 2021/4/12.
 */


fun Activity?.hideSoftInput(flag: Int = InputMethodManager.HIDE_NOT_ALWAYS) {
    if (this == null) {
        return
    }
    val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    currentFocus?.let {
        imm.hideSoftInputFromWindow(it.windowToken, flag)
    }
}