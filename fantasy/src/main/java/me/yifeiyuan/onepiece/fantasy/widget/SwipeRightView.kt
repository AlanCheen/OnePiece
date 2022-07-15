package me.yifeiyuan.onepiece.fantasy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper

/**
 * Created by 程序亦非猿 on 2021/4/7.
 * todo
 */
class SwipeRightView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "SwipeRightView"
    }

    private lateinit var mDragHelper: ViewDragHelper

    private lateinit var contentView: View
    private lateinit var hiddenView: View

    private var actionEnable = false

    private val dragCallback: ViewDragHelper.Callback = object : ViewDragHelper.Callback() {
        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
            hiddenView.left = contentView.width + left
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            mDragHelper.settleCapturedViewAt(0, contentView.top)
            invalidate()
            invokeCallbackIfNeed()
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return if (child === contentView) {
                hiddenView.width / 2
            } else 0
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            if (child.left == 0 && dx > 0 || hasReach() && dx < 0) {
                return child.left
            }
            if (hiddenView.left > contentView.width && contentView.left > 0) {
                mDragHelper.cancel()
                return child.left
            }
            lastDx = dx.toFloat()
            return Math.max(0 - hiddenView.width / 2, left)
        }

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return actionEnable && child === contentView && !canChildScrollHorizontally(child)
        }
    }

    init {
        orientation = HORIZONTAL
        mDragHelper = ViewDragHelper.create(this, 1f, dragCallback)
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        require(childCount == 2) { javaClass.simpleName + " must have 2 children" }
        contentView = getChildAt(0)
        hiddenView = getChildAt(1)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = measuredHeight
        val width = measuredWidth
        val heightSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY)
        val widthSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY)
        contentView.measure(widthSpec, heightSpec)
        hiddenView.measure(widthSpec, heightSpec)
        setMeasuredDimension(width, height)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val willDragHelperIntercept = mDragHelper.shouldInterceptTouchEvent(ev)
        if (willDragHelperIntercept) {
            requestDisallowInterceptTouchEvent(true)
        }
        return willDragHelperIntercept
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mDragHelper.continueSettling(true)) {
            invalidate()
        }
    }


    private fun hasReach(): Boolean {
        // TODO: 2021/4/7
//        return contentView.getLeft() + UIUtils.getScreenWidth() / 2 <= 0;
        return contentView.left + 200 <= 0
    }

    private var lastDx = 0f
    private fun invokeCallbackIfNeed() {
        val left = hiddenView.left
        val maxLeft = contentView.width - contentView.width / 5
        val inArea = left <= maxLeft
        if (lastDx < 0 && inArea) {
            callback?.onAction()
        }
    }

    private fun canChildScrollHorizontally(child: View): Boolean {
        return ViewCompat.canScrollHorizontally(child, 1)
    }

    private var callback: Callback? = null

    fun setActionEnable(enable: Boolean) {
        actionEnable = enable
    }

    interface Callback {
        fun onAction()
    }


}