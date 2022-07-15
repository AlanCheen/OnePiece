package me.yifeiyuan.onepiece.fantasy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.lang.reflect.Field

/**
 * Created by 程序亦非猿 on 2022/7/14.
 */
class FantasySwipeRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(
    context,
    attrs
) {
    /**
     * SwipeRefreshLayout 默认不支持 wrap_content
     */
    var wrapContentSupport: Boolean = false
    var targetField: Field? = null

    init {
        try {
            targetField = SwipeRefreshLayout::class.java.getDeclaredField("mTarget")
            targetField?.isAccessible = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (wrapContentSupport && targetField != null) {
            val heightMode = MeasureSpec.getMode(heightMeasureSpec)
            if (heightMode == MeasureSpec.AT_MOST) {
                try {
                    val mTarget = targetField!!.get(this) as View
                    mTarget.measure(
                        MeasureSpec.makeMeasureSpec(
                            this.measuredWidth - this.paddingLeft - this.paddingRight,
                            MeasureSpec.getMode(widthMeasureSpec)
                        ),
                        MeasureSpec.makeMeasureSpec(
                            this.measuredHeight - this.paddingTop - this.paddingBottom,
                            MeasureSpec.getMode(heightMeasureSpec)
                        )
                    )
                    setMeasuredDimension(measuredWidth, mTarget.measuredHeight)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }
    }
}