package me.yifeiyuan.onepiece.dev.ui

import me.yifeiyuan.onepiece.dev.domain.HomeUserCase
import me.yifeiyuan.onepiece.mvvm.BaseViewModel

/**
 * Created by 程序亦非猿 on 2022/7/6.
 */
class HomoViewModel(private val homeUserCase: HomeUserCase = HomeUserCase()) : me.yifeiyuan.onepiece.mvvm.BaseViewModel() {

    fun getHomePageData() {
        homeUserCase.getHomePageData()
    }
}