package me.yifeiyuan.onepiece.pandora.ktx

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by 程序亦非猿 on 2021/4/7.
 */

inline fun <reified A : Activity> Context.start(intentConfig: Intent.() -> Unit = {}) {
    startActivity(Intent(this, A::class.java).apply(intentConfig))
}

fun Context.toPixel(dip: Int): Int {
    val scale: Float = resources.displayMetrics.scaledDensity
    return (dip * scale + 0.5).toInt()
}

fun Context.getScreenHeight(): Int {
    return resources.displayMetrics.heightPixels
}

fun Context.getScreenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

/**
 * @param fileName  例如 foo.json
 */
fun Context?.openAsset(fileName: String): String? {
    if (this == null) {
        return null
    }

    val stringBuilder = StringBuilder()
    try {
        val bufferedReader = BufferedReader(
            InputStreamReader(
                assets.open(fileName)
            )
        )
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return stringBuilder.toString()
}

fun Context?.copyTextToClipboard(text: CharSequence) {
    if (this == null) {
        return
    }
    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("", text)
    clipboardManager.setPrimaryClip(clip)
}

//inline fun <reified S>Context.getSystemService():S{
//    return getSystemService(S::class.java)
//}

fun Context?.showSoftInput(view: View, flag: Int = InputMethodManager.SHOW_FORCED) {
    if (this == null) {
        return
    }
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    imm.showSoftInput(view, flag)
}

/**
 * 判断网络是否连接
 * @return 是否有网络可用
 */
fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (manager.activeNetworkInfo != null) {
        return manager.activeNetworkInfo!!.isAvailable
    }
    return false
}