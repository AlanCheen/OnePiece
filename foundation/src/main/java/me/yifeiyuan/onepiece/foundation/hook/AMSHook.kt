package me.yifeiyuan.onepiece.foundation.hook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import java.lang.reflect.Field
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object AMSHook {

    /**
     * hook ams的方式
     * 可以同时hook startActivity  getApplicationContext().startActivity
     */
    fun hookAMS(callback: IActivityHookCallback) {
        val sdkInt = Build.VERSION.SDK_INT
        if (sdkInt > Build.VERSION_CODES.P) {
            hookAMSAfter28(callback)
        } else if (sdkInt >= Build.VERSION_CODES.O) {
            hookAMSAfter26(callback)
        } else {
            hookAmsBefore26(callback)
        }
    }

    @SuppressLint("PrivateApi")
    private fun hookAmsBefore26(callback: IActivityHookCallback) {
        // 第一步：获取 IActivityManagerSingleton
        try {
            val forName = Class.forName("android.app.ActivityManagerNative")
            val defaultField: Field = forName.getDeclaredField("gDefault")
            defaultField.setAccessible(true)
            val defaultValue: Any = defaultField.get(null)

            val forName2 = Class.forName("android.util.Singleton")
            val instanceField: Field = forName2.getDeclaredField("mInstance")
            instanceField.setAccessible(true)
            val iActivityManagerObject: Any = instanceField.get(defaultValue)

            // 第二步：获取我们的代理对象，这里因为 IActivityManager 是接口，我们使用动态代理的方式
            val iActivity = Class.forName("android.app.IActivityManager")
            val handler: InvocationHandler = AMSInvocationHandler(iActivityManagerObject, callback)
            val proxy: Any = Proxy.newProxyInstance(
                Thread.currentThread().contextClassLoader,
                arrayOf<Class<*>>(iActivity),
                handler
            )

            // 第三步：偷梁换柱，将我们的 proxy 替换原来的对象
            instanceField.set(defaultValue, proxy)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    @SuppressLint("PrivateApi")
    private fun hookAMSAfter26(callback: IActivityHookCallback) {
        // 第一步：获取 IActivityManagerSingleton
        try {
            val aClass = Class.forName("android.app.ActivityManager")
            val declaredField: Field = aClass.getDeclaredField("IActivityManagerSingleton")
            declaredField.setAccessible(true)
            val value: Any = declaredField.get(null)

            val singletonClz = Class.forName("android.util.Singleton")
            val instanceField: Field = singletonClz.getDeclaredField("mInstance")
            instanceField.setAccessible(true)
            val iActivityManagerObject: Any = instanceField.get(value)

            // 第二步：获取我们的代理对象，这里因为 IActivityManager 是接口，我们使用动态代理的方式
            val iActivity = Class.forName("android.app.IActivityManager")
            val handler: InvocationHandler = AMSInvocationHandler(iActivityManagerObject, callback)
            val proxy: Any = Proxy.newProxyInstance(
                Thread.currentThread().contextClassLoader,
                arrayOf<Class<*>>(iActivity),
                handler
            )

            // 第三步：偷梁换柱，将我们的 proxy 替换原来的对象
            instanceField.set(value, proxy)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    @SuppressLint("PrivateApi")
    private fun hookAMSAfter28(callback: IActivityHookCallback) {
        // 第一步：获取 IActivityManagerSingleton
        try {
            val clazz = Class.forName("android.app.ActivityTaskManager")
            val defaultFiled: Field = clazz.getDeclaredField("IActivityTaskManagerSingleton")
            defaultFiled.setAccessible(true)
            val defaultValue: Any = defaultFiled.get(null)

            //反射SingleTon
            val SingletonClass = Class.forName("android.util.Singleton")
            val mInstance: Field = SingletonClass.getDeclaredField("mInstance")
            mInstance.setAccessible(true)
            val iActivityManagerObject: Any = mInstance.get(defaultValue)
            if (iActivityManagerObject != null) {
                //开始动态代理，用代理对象替换掉真实的ActivityManager，瞒天过海
                val IActivityManagerIntercept = Class.forName("android.app.IActivityTaskManager")
                val handler: InvocationHandler =
                    AMSInvocationHandler(iActivityManagerObject, callback)
                val proxy: Any = Proxy.newProxyInstance(
                    Thread.currentThread().contextClassLoader,
                    arrayOf<Class<*>>(IActivityManagerIntercept),
                    handler
                )
                //现在替换掉这个对象
                mInstance.set(defaultValue, proxy)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    class AMSInvocationHandler(
        private val real: Any,
        private val callback: IActivityHookCallback?
    ) : InvocationHandler {
        @Throws(Throwable::class)
        override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
            val name = method.name
            if ("startActivity" == name) {
                try {
                    var index = 0
                    for (i in args.indices) {
                        if (args[i] is Intent) {
                            index = i
                            break
                        }
                    }
                    val intent = args[index] as Intent
                    if (intent != null && callback != null) {
                        callback.onStartActivity(intent)
                    }
                } catch (ignored: Throwable) {
                }
            }
            try {
                return method.invoke(real, *args)
            } catch (e: InvocationTargetException) {
                throw e.targetException
            }
        }
    }

    /**
     * startActivity监听回调器
     */
    interface IActivityHookCallback {
        /**
         * startActivity触发时回调
         */
        fun onStartActivity(intent: Intent?)
    }
}