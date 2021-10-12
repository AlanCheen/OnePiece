package me.yifeiyuan.onepiece.pandora

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by 程序亦非猿 on 2021/4/7.
 */

fun Context.toPixel(dip: Int): Int {
    val scale: Float = resources.displayMetrics.scaledDensity
    return (dip * scale + 0.5).toInt()
}

fun Context.getScreenHeight(): Int {
    return resources.displayMetrics.heightPixels
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

fun Context?.showSoftInput(view: View, flag: Int = InputMethodManager.SHOW_FORCED) {
    if (this == null) {
        return
    }
    val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    imm.showSoftInput(view, flag)
}

