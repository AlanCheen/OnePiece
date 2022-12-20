package me.yifeiyuan.onepiece.pandora.ktx.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Extensions for ViewGroup.
 *
 * Created by 程序亦非猿 on 2022/12/20.
 */


fun ViewGroup.inflate(resId: Int, attachToRoot: Boolean = true): View {
    return LayoutInflater.from(context).inflate(resId, this, attachToRoot)
}