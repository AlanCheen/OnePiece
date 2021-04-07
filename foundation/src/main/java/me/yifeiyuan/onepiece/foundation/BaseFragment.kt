package me.yifeiyuan.onepiece.foundation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Created by 程序亦非猿 on 2021/3/24.
 */
abstract class BaseFragment : Fragment() {

    lateinit var hostActivity: Activity

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        hostActivity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView(view, savedInstanceState)
        onInitData(view, savedInstanceState)
    }

    abstract fun onInitData(view: View, savedInstanceState: Bundle?)

    abstract fun onInitView(view: View, savedInstanceState: Bundle?)

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}