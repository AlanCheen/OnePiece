package me.yifeiyuan.onepiece.pandora

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by 程序亦非猿 on 2021/4/12.
 */

//InputMethodManager.HIDE_NOT_ALWAYS
fun Activity?.hideSoftInput(focusView: View, flag: Int = 0) {
    if (this == null) {
        return
    }
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    focusView.let {
        imm.hideSoftInputFromWindow(it.windowToken, flag)
    }
}

//InputMethodManager.HIDE_NOT_ALWAYS
fun Activity?.hideSoftInput(flag: Int = InputMethodManager.HIDE_NOT_ALWAYS) {
    if (this == null) {
        return
    }
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    if (currentFocus != null) {
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, flag)
    } else {
        imm.hideSoftInputFromWindow(window.decorView.windowToken, flag)
    }
}