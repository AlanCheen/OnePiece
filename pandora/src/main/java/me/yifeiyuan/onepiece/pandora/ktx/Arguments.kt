package me.yifeiyuan.onepiece.pandora.ktx

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by 程序亦非猿 on 2021/10/12.
 */

abstract class NullableArgument<T>(private val key: String, private val defaultValue: T?) :
    ReadOnlyProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return when (thisRef) {
            is Activity -> {
                getValue(thisRef.intent.extras, key, defaultValue)
            }
            is Fragment -> {
                getValue(thisRef.arguments, key, defaultValue)
            }
            else -> {
                throw UnsupportedOperationException("")
            }
        }
    }

    abstract fun getValue(bundle: Bundle?, key: String, defaultValue: T?): T?
}

abstract class NonNullArgument<T>(private val key: String, private val defaultValue: T) :
    ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return when (thisRef) {
            is Activity -> {
                return if (thisRef.intent.extras?.containsKey(key) == true) {
                    getValue(thisRef.intent.extras!!, key, defaultValue)
                } else {
                    defaultValue
                }
            }
            is Fragment -> {
                return if (thisRef.arguments?.containsKey(key) == true) {
                    getValue(thisRef.requireArguments(), key, defaultValue)
                } else {
                    defaultValue
                }
            }

            else -> {
                throw UnsupportedOperationException("")
            }
        }
    }

    abstract fun getValue(bundle: Bundle, key: String, defaultValue: T): T
}

class LongArgument(key: String, defaultValue: Long = 0) : NonNullArgument<Long>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: Long): Long {
        return bundle.getLong(key) ?: defaultValue
    }
}

class IntArgument(key: String, defaultValue: Int = 0) : NonNullArgument<Int>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: Int): Int {
        return bundle.getInt(key) ?: defaultValue
    }
}

class FloatArgument(key: String, defaultValue: Float = 0f) :
    NonNullArgument<Float>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: Float): Float {
        return bundle.getFloat(key) ?: defaultValue
    }
}

class BooleanArgument(key: String, defaultValue: Boolean = false) :
    NonNullArgument<Boolean>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: Boolean): Boolean {
        return bundle.getBoolean(key) ?: defaultValue
    }
}

class StringArgument(key: String, defaultValue: String = "") :
    NonNullArgument<String>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: String): String {
        return bundle.getString(key) ?: defaultValue
    }
}

//not tested 是不是 nullable 更好些？
class ParcelableArgument<P : Parcelable>(key: String, defaultValue: P) :
    NonNullArgument<P>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: P): P {
        return bundle.getParcelable<P>(key) ?: defaultValue
    }
}

//not tested
class BundleArgument(key: String, defaultValue: Bundle = Bundle()) :
    NonNullArgument<Bundle>(key, defaultValue) {
    override fun getValue(bundle: Bundle, key: String, defaultValue: Bundle): Bundle {
        return bundle.getBundle(key) ?: defaultValue
    }
}