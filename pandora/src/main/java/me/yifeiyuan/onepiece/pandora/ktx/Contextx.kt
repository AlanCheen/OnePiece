package me.yifeiyuan.onepiece.pandora.ktx

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.Service
import android.content.*
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Process
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Context 扩展
 *
 * Created by 程序亦非猿 on 2021/4/7.
 */

inline fun <reified A : Activity> Context.startActivity(intentBuilder: Intent.() -> Unit = {}) {
    val intent = Intent(this, A::class.java).apply(intentBuilder)
    if (this !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

inline fun <reified S : Service> Context.startService(intentBuilder: Intent.() -> Unit = {}) {
    startService(Intent(this, S::class.java).apply(intentBuilder))
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

fun Context?.showToast(text: CharSequence, isShort: Boolean = true) {
    val duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    if (this is Activity) {
        if (isActive()) {
            if (isMainThread()) {
                Toast.makeText(this, text, duration).show()
            } else {
                runOnMainThread {
                    Toast.makeText(this, text, duration).show()
                }
            }
        }
    } else {
        if (isMainThread()) {
            Toast.makeText(this, text, duration).show()
        } else {
            runOnMainThread {
                Toast.makeText(this, text, duration).show()
            }
        }
    }
}

fun Context?.showToast(resId: Int, isShort: Boolean = true) {
    val duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    if (this is Activity) {
        if (isActive()) {
            if (isMainThread()) {
                Toast.makeText(this, resId, duration).show()
            } else {
                runOnMainThread {
                    Toast.makeText(this, resId, duration).show()
                }
            }
        }
    } else {
        if (isMainThread()) {
            Toast.makeText(this, resId, duration).show()
        } else {
            runOnMainThread {
                Toast.makeText(this, resId, duration).show()
            }
        }
    }
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

/**
 * 判断当前是否是暗黑模式
 */
fun Context.isDarkMode(): Boolean {
    val uiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return uiMode == Configuration.UI_MODE_NIGHT_YES
}

/**
 * 注册本地广播
 */
fun Context.registerLocalBroadcast(
    broadcastReceiver: BroadcastReceiver,
    intentFilter: IntentFilter
) {
    LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter)
}

fun Context.unregisterLocalBroadcast(broadcastReceiver: BroadcastReceiver) {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
}

fun Context.sendLocalBroadcast(intent: Intent) {
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
}

fun Context.sendLocalBroadcastSync(intent: Intent) {
    LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent)
}

/**
 * 获取当前进程名
 * @return 进程名
 */
fun Context.getProgressName(): String {

    val pid = Process.myPid()

    val mActivityManager: ActivityManager =
        getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    val list: List<RunningAppProcessInfo> = mActivityManager.runningAppProcesses

    for (appProcess in list) {
        if (appProcess.pid == pid) {
            return appProcess.processName
        }
    }
    return ""
}