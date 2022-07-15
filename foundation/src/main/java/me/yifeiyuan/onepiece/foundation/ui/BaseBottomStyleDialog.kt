package me.yifeiyuan.onepiece.foundation.ui

import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import me.yifeiyuan.onepiece.foundation.R

/**
 * Created by 程序亦非猿 on 2021/9/27.
 */
abstract class BaseBottomStyleDialog : BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.foundation_BottomSheetDialogStyle)
    }

    override fun onSetupWindow() {
        super.onSetupWindow()

        dialog?.window?.let {
            it.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            it.attributes.gravity = setupDialogGravity()
            it.attributes = it.attributes
        }
    }

    open fun setupDialogGravity() = Gravity.BOTTOM

}