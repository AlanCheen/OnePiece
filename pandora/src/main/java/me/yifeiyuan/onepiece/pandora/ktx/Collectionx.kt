package me.yifeiyuan.onepiece.pandora.ktx

/**
 * Created by 程序亦非猿 on 2022/6/28.
 *
 * 集合相关 KTX
 */


fun stringMap(init: (MutableMap<String, String>) -> Unit): MutableMap<String, String> {
    return mutableMapOf<String, String>().apply {
        init.invoke(this)
    }
}