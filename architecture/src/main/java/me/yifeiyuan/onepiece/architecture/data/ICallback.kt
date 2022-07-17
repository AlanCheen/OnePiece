package me.yifeiyuan.onepiece.architecture.data

/**
 * Created by 程序亦非猿 on 2021/9/18.
 */
interface ICallback<Result, Error> {

    fun onStart() {}

    fun onSuccess(result: Result) {
        this.onComplete(true)
    }

    fun onError(error: Error) {
        this.onComplete(false)
    }

    fun onComplete(success: Boolean) {}

}