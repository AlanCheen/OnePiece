package me.yifeiyuan.onepiece.architecture.data

/**
 * Created by 程序亦非猿 on 2021/9/18.
 */
interface DialogCallback<R, E> : ICallback<R, E> {

    fun onShowLoading()

    fun onHideLoading()

    fun onDismiss()
}