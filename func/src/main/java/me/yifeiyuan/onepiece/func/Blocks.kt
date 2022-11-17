package me.yifeiyuan.onepiece.func

/**
 * Created by 程序亦非猿 on 2022/9/28.
 */

typealias Block = () -> Unit

typealias UnitBlock = () -> Unit

typealias IntBlock = () -> Int

typealias LongBlock = () -> Long

typealias CharBlock = () -> Char

typealias BooleanBlock = () -> Boolean

typealias StringBlock = () -> String

typealias NumberBlock = () -> Number

typealias DoubleBlock = () -> Double

typealias ValueBlock<R> = () -> R
typealias ValueBlock1<P, R> = (P) -> R
typealias ValueBlock2<P1, P2, R> = (P1, P2) -> R
typealias ValueBlock3<P1, P2, P3, R> = (P1, P2, P3) -> R
