package me.yifeiyuan.onepiece.pandora

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by 程序亦非猿 on 2021/4/7.
 */

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