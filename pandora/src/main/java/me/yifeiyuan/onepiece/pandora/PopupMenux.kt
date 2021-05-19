package me.yifeiyuan.onepiece.pandora

import android.annotation.SuppressLint
import android.widget.PopupMenu
import androidx.appcompat.view.menu.MenuPopupHelper
import java.lang.reflect.Field

/**
 * Created by 程序亦非猿 on 2021/5/10.
 */


/**
 * 默认情况下  PopupMenu 不会展示 icon，即便你在菜单里定义了 icon。
 * 需要反射调用开启
 */
@SuppressLint("RestrictedApi")
fun PopupMenu.showIcon() {
    try {
        val field: Field = PopupMenu::class.java.getDeclaredField("mPopup")
        field.isAccessible = true
        (field.get(this) as MenuPopupHelper).setForceShowIcon(true)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}