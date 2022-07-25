package me.yifeiyuan.onepiece.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.yifeiyuan.onepiece.architecture.core.LiveHandler
import me.yifeiyuan.onepiece.pandora.ktx.*

class MainActivity : AppCompatActivity() {

    private lateinit var liveHandler: LiveHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveHandler = LiveHandler(this)

        val s: String = "1"
        s.ifIs<String> {

        }

        s.runAs<String> {

        }
        s.ifBlank {

        }
        s.ifEmpty {

        }
        runWithTryCatch {

        }
    }

    fun testContext() {
        start<MainActivity>()
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
    }


    private fun testFunction(func: Function0<String>) {
        func()
    }


    private fun testC() {

//        "b".ifEmpty {
//            "aa".toString()
//        }
//        "a".ifEmpty {
//            "2"
//        }
    }
}