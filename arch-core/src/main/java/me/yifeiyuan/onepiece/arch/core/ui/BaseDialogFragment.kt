package me.yifeiyuan.onepiece.arch.core.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by 程序亦非猿 on 2021/4/10.
 */
abstract class BaseDialogFragment : DialogFragment() {

    protected var contentView: View? = null

    lateinit var hostActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = activity as FragmentActivity
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (savedInstanceState != null && dismissWhenRecover()) {
            dismissAllowingStateLoss()
            return super.onCreateDialog(savedInstanceState)
        }
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val view = onCreateDialogContentView()
        dialog.setContentView(view)
        contentView = view
        //把背景修改为透明 不然设置圆角会露出黑色背景
        (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        onInit(view, dialog, savedInstanceState)
        onSetupWindow()
        return dialog
    }

    open fun onCreateDialogContentView(): View {
        return View.inflate(context, getLayoutId(), null)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onInit(view: View, dialog: Dialog, savedInstanceState: Bundle?)

    open fun onSetupWindow() {}

    open fun dismissWhenRecover() = true

    fun show(fragmentActivity: FragmentActivity, tag: String?) {
        this.show(fragmentActivity.supportFragmentManager, tag)
    }

    fun showSingleton(fragmentActivity: FragmentActivity, tag: String?) {
        tag?.let {
            val preInstance = fragmentActivity.supportFragmentManager.findFragmentByTag(tag)
            if (preInstance is BaseDialogFragment) {
                preInstance.dismissAllowingStateLoss()
            }
        }

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

    override fun dismiss() {
        if (fragmentManager == null) {
            return
        }
        super.dismiss()
    }

    override fun dismissAllowingStateLoss() {
        if (fragmentManager == null) {
            return
        }
        super.dismissAllowingStateLoss()
    }
}