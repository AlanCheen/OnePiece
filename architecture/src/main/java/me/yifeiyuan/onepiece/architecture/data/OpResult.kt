package me.yifeiyuan.onepiece.architecture.data

/**
 * Created by 程序亦非猿 on 2022/7/17.
 *
 * 数据结果的基础类
 */
data class OpResult<D, E>(val data: D, val error: E? = null) {


}