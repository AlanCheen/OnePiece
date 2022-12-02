package me.yifeiyuan.onepiece.arch.mvi

/**
 * Created by 程序亦非猿 on 2022/12/2.
 */
sealed class State{

    object Idle :State()

    object Loading:State()


}