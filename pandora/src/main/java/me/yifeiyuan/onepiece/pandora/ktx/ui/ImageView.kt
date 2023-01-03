package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView

/**
 * Created by 程序亦非猿 on 2023/1/3.
 */

/**
 * 不可以调用 bitmap.recycle() 方法，有部分机子会导致 crash。
 */
fun ImageView.recycle() {
    try {
        (drawable as BitmapDrawable).let {
            setImageResource(0)
            setImageDrawable(null)
//            it.bitmap.recycle()
            it.callback = null
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}