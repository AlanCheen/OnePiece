package me.yifeiyuan.onepiece.pandora.ktx

import java.lang.reflect.Field

/**
 * Created by 程序亦非猿 on 2022/11/22.
 */

fun Class<*>.getDeclaredAccessibleField(name: String): Field {
    val field = getDeclaredField(name)
    field.isAccessible = true
    return field
}

inline fun <reified T>Class<T>.getNestedClass(vararg names:String): Class<*> {
    var nestedClassName = T::class.java.name
    for (name in names) {
        nestedClassName += "$$name"
    }
    return Class.forName(nestedClassName)
}