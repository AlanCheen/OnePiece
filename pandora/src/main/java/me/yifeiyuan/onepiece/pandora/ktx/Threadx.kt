package me.yifeiyuan.onepiece.pandora.ktx

/**
 * Created by 程序亦非猿 on 2022/7/15.
 */


/**
 * 在一个新线程上执行
 * @param action
 */
fun <T> T.startNewThread(action: T.() -> Unit) {
    Thread {
        action()
    }.start()
}

fun <T> T.mainThread(block: T.() -> Unit) {
    mainThreadHandler.post {
        block.invoke(this)
    }
}