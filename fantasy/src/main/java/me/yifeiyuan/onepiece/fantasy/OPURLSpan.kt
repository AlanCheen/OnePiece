package me.yifeiyuan.onepiece.fantasy

import android.text.TextPaint
import android.text.style.URLSpan
import android.view.View

/**
 * Created by 程序亦非猿 on 2022/11/21.
 */
class OPURLSpan(
    var url: String,
    var textColor: Int,
    var isUnderlineText: Boolean,
    var onClick: (url: String) -> Unit
) : URLSpan(url) {

    override fun onClick(widget: View) {
        onClick.invoke(url)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = isUnderlineText
        ds.color = textColor
    }
}