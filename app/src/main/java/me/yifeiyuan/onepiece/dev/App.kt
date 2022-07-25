package me.yifeiyuan.onepiece.dev

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Created by 程序亦非猿 on 2022/7/25.
 */
class App : Application(), ViewModelStoreOwner {

    val appViewModelStore = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }
}