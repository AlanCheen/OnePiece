package me.yifeiyuan.onepiece.architecture.ui

/**
 * Created by 程序亦非猿 on 2022/7/26.
 */
sealed class State{

    object Idle :State()

    object Loading:State()


}
