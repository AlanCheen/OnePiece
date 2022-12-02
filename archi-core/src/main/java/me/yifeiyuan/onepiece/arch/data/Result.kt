package me.yifeiyuan.onepiece.arch.data

/**
 * Created by 程序亦非猿 on 2022/7/17.
 *
 * 数据结果的基础类
 */
data class Result<D, E>(val data: D, val error: E? = null) {


}