package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by 程序亦非猿 on 2022/12/29.
 */

fun <T : RecyclerView.ViewHolder> T.getString(@StringRes resId: Int): String {
    return itemView.context.getString(resId)
}

fun <T : RecyclerView.ViewHolder> T.getString(
    @StringRes resId: Int,
    vararg formatArgs: Any
): String {
    return itemView.context.getString(resId, *formatArgs)
}

fun <T : RecyclerView.ViewHolder> T.getDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(itemView.context, id)
}

@ColorInt
fun <T : RecyclerView.ViewHolder> T.getColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(itemView.context, id)
}

fun <T : RecyclerView.ViewHolder> T.getColorStateList(@ColorRes id: Int): ColorStateList? {
    return ContextCompat.getColorStateList(itemView.context, id)
}