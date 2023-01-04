package me.yifeiyuan.onepiece.multistatelayout

/**
 * Created by 程序亦非猿 on 2022/12/7.
 */
sealed class State {
    object CONTENT : State()
    object EMPTY : State()
    object LOADING : State()
    sealed class ERROR(var throwable: Throwable? = null) : State()
}