package me.yifeiyuan.onepiece.pandora.ktx

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * Activity 的扩展
 *
 * @see clearFragments
 *
 * Created by 程序亦非猿 on 2021/4/12.
 */

fun Activity?.isActive(): Boolean {
    if (this == null) {
        return false
    }
    return !isDestroyed && !isFinishing
}

fun Activity?.isInactive(): Boolean {
    return this == null || isDestroyed || isFinishing
}

//InputMethodManager.HIDE_NOT_ALWAYS
fun Activity.hideSoftInput(focusView: View, flag: Int = 0) {
    if (isInactive()) {
        return
    }
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    focusView.let {
        imm.hideSoftInputFromWindow(it.windowToken, flag)
    }
}

//InputMethodManager.HIDE_NOT_ALWAYS
fun Activity.hideSoftInput(flag: Int = InputMethodManager.HIDE_NOT_ALWAYS) {
    if (isInactive()) {
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

/**
 * This function is used to get the root view from the Activity Context.
 * Get the DecorView from the Window object of the activity and finds the View object
 * with the android.R.id.content ID.
 */
fun Activity.getRootView(): ViewGroup = window.decorView.findViewById(android.R.id.content)

/**
 * 沉浸式&全屏 todo test
 */
//fun Activity.enableImmersiveMode(){
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//        window.statusBarColor = Color.TRANSPARENT
//    } else {
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//    }
//}