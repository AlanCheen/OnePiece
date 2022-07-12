package me.yifeiyuan.onepiece.pandora.ktx

/**
 * Created by 程序亦非猿 on 2022/6/28.
 *
 * 集合相关 KTX
 */

/**
 * LinkedHashMap<String, String> 的类型别名
 */
typealias StringMap = LinkedHashMap<String, String>

/**
 * 快速构建一个 MutableMap<String, String>
 * @return MutableMap<String, String>
 */
fun mapOfString(init: (MutableMap<String, String>) -> Unit): MutableMap<String, String> {
    return mutableMapOf<String, String>().apply {
        init.invoke(this)
    }
}
