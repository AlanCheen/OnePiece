package me.yifeiyuan.onepiece.pandora.ktx

import android.os.Bundle

/**
 * Bundle 扩展
 *
 * Created by 程序亦非猿 on 2021/7/16.
 */

fun Bundle.getString(key: String, defaultValue: String, block: (String) -> Unit): Bundle {
    block(getString(key, defaultValue))
    return this
}

fun Bundle.getStringIfContains(key: String, block: (String) -> Unit): Bundle {
    if (containsKey(key)) {
        block(getString(key).orEmpty())
    }
    return this
}

fun Bundle.getBoolean(key: String, defaultValue: Boolean, block: (Boolean) -> Unit): Bundle {
    block(getBoolean(key, defaultValue))
    return this
}

fun Bundle.getBooleanIfContains(key: String, block: (Boolean) -> Unit): Bundle {
    if (containsKey(key)) {
        block(getBoolean(key))
    }
    return this
}
