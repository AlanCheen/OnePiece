package me.yifeiyuan.onepiece.arch.ability

import java.util.*
import kotlin.reflect.KClass

/**
 * Created by 程序亦非猿 on 2023/3/28.
 */
object AbilityManager {

    class AbilityNotFoundException : Exception()

    private val abilities = LinkedHashMap<Class<*>, Any>()

    fun <T : Ability> register(clazz: Class<T>, instance: Any) {
        abilities[clazz] = instance
    }

    fun <T : Ability> getAbility(clazz: Class<T>): T? {
        return abilities[clazz] as T?
    }

    fun <T : Ability> getAbilityOrThrow(clazz: Class<T>): T {
        return (abilities[clazz] as T?) ?: throw AbilityNotFoundException()
    }
}