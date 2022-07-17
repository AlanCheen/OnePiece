package me.yifeiyuan.onepiece.dev.ui

import me.yifeiyuan.onepiece.dev.domain.HomeUserCase
import me.yifeiyuan.onepiece.architecture.core.BaseViewModel

/**
 * Created by 程序亦非猿 on 2022/7/6.
 */
class HomoViewModel(private val homeUserCase: HomeUserCase = HomeUserCase()) : BaseViewModel() {

    fun getHomePageData() {
        homeUserCase.getHomePageData()
    }
}