package me.yifeiyuan.onepiece.fantasy

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
import com.google.android.material.animation.AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR

/**
 * Created by 程序亦非猿 on 2021/4/15.
 *
 * 当滑动 RecyclerView 时，底部的 View 跟着展示或隐藏
 *
 * 代码实现参考 HideBottomViewOnScrollBehavior
 */
class HideBottomViewOnScroll (var bottomView: View) {

    private val ENTER_ANIMATION_DURATION = 225L
    private val EXIT_ANIMATION_DURATION = 175L

    private val STATE_SCROLLED_DOWN = 1
    private val STATE_SCROLLED_UP = 2

    private var currentState = 2
    private var currentAnimator: ViewPropertyAnimator? = null

    fun setupWithRecyclerView(recyclerView: RecyclerView){
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                onScroll(dy)
            }
        })
    }

    private fun onScroll(dy: Int) {
        if (currentState != STATE_SCROLLED_DOWN && dy > 0) {
            slideDown()
        } else if (currentState != STATE_SCROLLED_UP && dy < 0) {
            slideUp()
        }
    }

    private fun slideUp() {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            bottomView.clearAnimation()
        }
        currentState = STATE_SCROLLED_UP
        animateChildTo(
                bottomView,
                0,
                ENTER_ANIMATION_DURATION,
                LINEAR_OUT_SLOW_IN_INTERPOLATOR
        )
    }

    private fun slideDown() {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            bottomView.clearAnimation()
        }
        currentState = STATE_SCROLLED_DOWN
        animateChildTo(
                bottomView,
                bottomView.height,
                EXIT_ANIMATION_DURATION,
                FAST_OUT_LINEAR_IN_INTERPOLATOR
        )
    }

    private fun animateChildTo(
            child: View,
            targetY: Int,
            duration: Long,
            interpolator: TimeInterpolator
    ) {
        currentAnimator =
                child.animate().translationY(targetY.toFloat()).setInterpolator(interpolator)
                        .setDuration(duration).setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                this@HideBottomViewOnScroll.currentAnimator = null
                            }
                        })
    }
}