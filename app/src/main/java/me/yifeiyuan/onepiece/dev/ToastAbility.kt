package me.yifeiyuan.onepiece.dev

import android.widget.Toast
import me.yifeiyuan.onepiece.arch.ability.Ability
import me.yifeiyuan.onepiece.dev.App.Companion.application
import me.yifeiyuan.onepiece.pandora.ktx.runOnMainThread

/**
 * Created by 程序亦非猿 on 2023/3/28.
 */
interface ToastAbility : Ability {

    fun show(message: CharSequence)

}

class ToastAbilityImpl :ToastAbility{
    override fun show(message: CharSequence) {
        runOnMainThread {
            Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
        }
    }
}