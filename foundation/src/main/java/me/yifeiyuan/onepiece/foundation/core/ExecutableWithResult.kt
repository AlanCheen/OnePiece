package me.yifeiyuan.onepiece.foundation.core

/**
 * Created by 程序亦非猿 on 2022/6/13.
 */
interface ExecutableWithResult<P, R> {

    /**
     * @param params 执行所需参数
     */
    fun execute(params: P): R

}