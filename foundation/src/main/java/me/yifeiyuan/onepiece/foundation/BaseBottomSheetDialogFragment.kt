package me.yifeiyuan.onepiece.foundation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by 程序亦非猿 on 2021/3/24.
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, getLayoutId(), null)
        dialog.setContentView(view)

        //把背景修改为透明 不然设置圆角会露出黑色背景
        (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        onInitView(view)
        onInitData()
        return dialog
    }

    abstract fun onInitData()

    abstract fun onInitView(view: View?)

    @LayoutRes
    abstract fun getLayoutId(): Int
}