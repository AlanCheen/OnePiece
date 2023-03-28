package me.yifeiyuan.onepiece.dev

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Created by 程序亦非猿 on 2022/7/25.
 */
class App : Application(), ViewModelStoreOwner {

    val appViewModelStore = ViewModelStore()

    companion object{
        lateinit var application : Context
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }
}