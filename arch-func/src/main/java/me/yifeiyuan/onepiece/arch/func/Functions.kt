package me.yifeiyuan.onepiece.arch.func

/**
 * Created by 程序亦非猿 on 2022/9/28.
 */

typealias Func = () -> Unit

typealias UnitFunc = () -> Unit

typealias IntFunc = () -> Int

typealias LongFunc = () -> Long

typealias CharFunc = () -> Char

typealias BooleanFunc = () -> Boolean

typealias StringFunc = () -> String

typealias NumberFunc = () -> Number

typealias DoubleFunc = () -> Double

typealias ValueFunc<R> = () -> R
typealias ValueFunc1<P, R> = (P) -> R
typealias ValueFunc2<P1, P2, R> = (P1, P2) -> R
typealias ValueFunc3<P1, P2, P3, R> = (P1, P2, P3) -> R
