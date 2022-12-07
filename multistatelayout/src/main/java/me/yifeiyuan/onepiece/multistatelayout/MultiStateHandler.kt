package me.yifeiyuan.onepiece.multistatelayout

/**
 * todo 多状态管理
 * Created by 程序亦非猿 on 2022/12/7.
 */
class MultiStateHandler {


    fun showLoading(data: Any?) {

        onStateChanged(State.LOADING)
    }

    fun showContent(data: Any) {

        onStateChanged(State.CONTENT)
    }

    fun showEmpty(data: Any?) {

        onStateChanged(State.EMPTY)
    }

    fun onStateChanged(state: State) {
        listener?.onStateChanged(state)
    }

    var listener: OnStateChangedListener? = null

     interface OnStateChangedListener{
         fun onStateChanged(state: State)
     }
}