package me.yifeiyuan.onepiece.foundation.data

/**
 * Created by 程序亦非猿 on 2021/9/18.
 */
interface ICallback<R, E> {

    fun onStart() {}

    fun onSuccess(result: R)

    fun onError(error: E)

//    fun onComplete() {}

}