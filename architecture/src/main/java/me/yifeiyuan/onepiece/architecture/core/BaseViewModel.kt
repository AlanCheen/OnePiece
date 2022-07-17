package me.yifeiyuan.onepiece.architecture.core

import androidx.lifecycle.ViewModel

/**
 * Created by 程序亦非猿 on 2021/9/18.
 */
abstract class BaseViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}