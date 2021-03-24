package me.yifeiyuan.onepiece.foundation

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(getLayoutId(),container,false)
        return root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}