package me.yifeiyuan.onepiece.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import me.yifeiyuan.onepiece.arch.ability.AbilityManager
import me.yifeiyuan.onepiece.dev.test.TestLiveComponents
import me.yifeiyuan.onepiece.arch.func.IntFunc
import me.yifeiyuan.onepiece.arch.func.ValueFunc
import me.yifeiyuan.onepiece.pandora.ktx.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val s: String = "1"
        s.ifIs<String> {

        }

        s.runAs<String> {

        }
        s.ifEmpty {
            ""
        }
        tryExecute {

        }

        tryExecute(
            { e ->
                e.printStackTrace()
            },
            {

            }
        ) {
        }

        AbilityManager.register(ToastAbility::class.java, ToastAbilityImpl())
    }

    fun testContext() {
        startActivity<MainActivity>()
    }

    override fun onResume() {
        super.onResume()

        testFunction {
            "a"
        }

        testFunction(object : Function0<String> {
            override fun invoke(): String {
                return ""
            }
        })

        runOnNewThread {

        }

        testValueBlock("test") {
            "android"
        }

        AbilityManager.getAbility(ToastAbility::class.java)?.show("Toast Ability test")
    }


    private fun testFunction(func: Function0<String>) {
        func()
    }

    fun testLiveComponents(view: View) {
        startActivity<TestLiveComponents>() {

        }
    }

    private fun testIntFunc(intFunc: IntFunc) {
        intFunc()
    }

    private fun testValueBlock(p: String, valueBlock: ValueFunc<String?>) {
        val result = valueBlock()

    }
}