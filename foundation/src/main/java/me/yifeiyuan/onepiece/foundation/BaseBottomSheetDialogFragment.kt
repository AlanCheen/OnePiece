package me.yifeiyuan.onepiece.foundation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by 程序亦非猿 on 2021/3/24.
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    protected var contentView: View? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, getLayoutId(), null)
        dialog.setContentView(view)
        contentView = view
        //把背景修改为透明 不然设置圆角会露出黑色背景
        (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        onInitView(view, dialog, savedInstanceState)
        onInitData(view, dialog, savedInstanceState)
        return dialog
    }

    abstract fun onInitData(view: View, dialog: Dialog, savedInstanceState: Bundle?)

    abstract fun onInitView(view: View?, dialog: Dialog, savedInstanceState: Bundle?)

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * https://stackoverflow.com/questions/35937453/set-state-of-bottomsheetdialogfragment-to-expanded
     *
     * When using AndroidX, the resource previously found at android.support.design.R.id.design_bottom_sheet
     * can now be found at com.google.android.material.R.id.design_bottom_sheet.
     */
    override fun onStart() {
        super.onStart()
        if (isExpanded()) {
            try {
                val view = (dialog as BottomSheetDialog).delegate.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

                view?.let {
                    BottomSheetBehavior.from(it)
                            .state = BottomSheetBehavior.STATE_EXPANDED
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun isExpanded(): Boolean {
        return false
    }

    fun show(fragmentActivity: FragmentActivity, tag: String?) {
        this.show(fragmentActivity.supportFragmentManager, tag)
    }

    override fun show(transaction: FragmentTransaction, tag: String?): Int {
        return super.show(transaction, tag)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showNow(manager: FragmentManager, tag: String?) {
        try {
            super.showNow(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}