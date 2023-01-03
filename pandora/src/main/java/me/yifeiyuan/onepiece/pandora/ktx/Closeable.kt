package me.yifeiyuan.onepiece.pandora.ktx

import java.io.Closeable

/**
 * Created by 程序亦非猿 on 2023/1/3.
 */

fun Closeable?.closeQuietly(){
    this?.let {
        try {
            it.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}