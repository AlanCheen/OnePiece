package me.yifeiyuan.onepiece.foundation

import android.app.ActivityThread
import android.app.Application

/**
 * Created by 程序亦非猿 on 2022/11/17.
 */


/**
 * 获取当前 Application Context
 */
fun getCurrentApplication(): Application = ActivityThread.currentApplication()