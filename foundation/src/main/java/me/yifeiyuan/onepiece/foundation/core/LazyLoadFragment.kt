package me.yifeiyuan.onepiece.foundation.core

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * Created by 程序亦非猿 on 2021/4/22.
 *
 * 一个在 ViewPager 中使用的，有懒加载的，生命周期事件更合理的 Fragment。
 *
 * viewLifecycleOwner.lifecycle 反应的生命周期是跟随 Activity 的，在 ViewPager 中的 Fragment 的生命周期并不完全适用
 * 例如 Fragment 实际上并不可见，但是它还会收到 ON_RESUME 事件，这是不正确的，会导致在不可见的情况下去请求数据、更新 UI。
 *
 * 所以自定义一个 lazyLifecycle 重新定义 ON_RESUME 和 ON_PAUSE 等事件的触发实际，更符合 Fragment 在 ViewPager 中的表现
 */
abstract class LazyLoadFragment : BaseFragment() {

    companion object {
        private const val TAG = "LazyLoadFragment"
    }

    private lateinit var lazyLifecycle: LifecycleRegistry
    private lateinit var lazyLifecycleOwner: LifecycleOwner

    private var didLazyLoad = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lazyLifecycleOwner = LifecycleOwner {
            lazyLifecycle
        }
        lazyLifecycle = LifecycleRegistry(lazyLifecycleOwner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lazyLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        super.onViewCreated(view, savedInstanceState)

        if (userVisibleHint) {
            onLazyLoad()
        }
    }

    //setUserVisibleHint 调用在 onCreateView 之前
    //被 FragmentPagerAdapter调用
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(TAG, "setUserVisibleHint() called with: isVisibleToUser = $isVisibleToUser")
        if (isVisibleToUser) {
            if (!didLazyLoad && view != null) {
                didLazyLoad = true
                onLazyLoad()
            }
        }

        // isVisibleToUser==true 发 ON_RESUME
        if (view != null) {
            lazyLifecycle.handleLifecycleEvent(if (isVisibleToUser) Lifecycle.Event.ON_RESUME else Lifecycle.Event.ON_PAUSE)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called $userVisibleHint")
        if (userVisibleHint && view != null) {
            lazyLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        }
    }

    /**
     * 在 ViewPager 中，应该只有当前被选中的 Item 才能接受 ON_PAUSE
     */
    override fun onPause() {
        Log.d(TAG, "onPause() called $userVisibleHint")
        if (userVisibleHint && view != null) {
            lazyLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }
        super.onPause()
    }

    /**
     * 在 ViewPager 中，应该只有当前被选中的 Item 才能接受 ON_RESUME
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called $userVisibleHint")
        if (userVisibleHint && view != null) {
            lazyLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    /**
     * 当Activity退到栈后，所有 Fragment 都应该接收到 ON_STOP
     */
    override fun onStop() {
        Log.d(TAG, "onStop() called $userVisibleHint")
        if (view != null) {
            lazyLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        }
        super.onStop()
    }

    /**
     * 同 onStop
     */
    override fun onDestroyView() {
        if (view != null) {
            lazyLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        }
        super.onDestroyView()
        Log.d(TAG, "onDestroyView() called $userVisibleHint")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called $userVisibleHint")
    }

    //请求数据
    abstract fun onLazyLoad()

}