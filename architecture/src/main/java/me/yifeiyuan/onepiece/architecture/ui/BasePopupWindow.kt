package me.yifeiyuan.onepiece.architecture.ui

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.PopupWindow

/**
 * Created by 程序亦非猿 on 2021/4/7.
 */
open class BasePopupWindow(var context: Context) :PopupWindow(){


    init {

        isFocusable = true
        isOutsideTouchable = true

        // 实例化一个ColorDrawable颜色为透明
        val colorDrawable = ColorDrawable(0x00000000)
        // 设置该背景到popup，并且并不会影响你的背景；
        // 若不设置background drawable，则在6.0以下，点击window外区域不会消失
        setBackgroundDrawable(colorDrawable)

    }


    private fun setWindowAlpha(activity: Activity, alpha: Float) {
        val window = activity.window
        val lp = window.attributes
        lp.alpha = alpha
        if (alpha == 1f) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) //Fix HuaWei
        }
        window.attributes = lp
    }
}