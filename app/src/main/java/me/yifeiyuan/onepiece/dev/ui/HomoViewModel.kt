package me.yifeiyuan.onepiece.dev.ui

import me.yifeiyuan.onepiece.dev.domain.HomeUserCase
import me.yifeiyuan.onepiece.foundation.core.BaseViewModel

/**
 * Created by 程序亦非猿 on 2022/7/6.
 */
class HomoViewModel(val homeUserCase: HomeUserCase = HomeUserCase()) : BaseViewModel() {


    override fun onStart() {
        TODO("Not yet implemented")
    }

    fun getHomePageData() {
        homeUserCase.getHomePageData()
    }
}