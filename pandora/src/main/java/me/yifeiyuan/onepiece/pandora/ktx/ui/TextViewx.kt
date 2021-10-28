package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 * Created by 程序亦非猿 on 2021/10/12.
 */


fun <T : TextView> T.textColor(resId: Int): T {
    setTextColor(ContextCompat.getColor(context, resId))
    return this
}

fun <T : TextView> T.text(resId: Int): T {
    text = context.resources.getString(resId)
    return this
}