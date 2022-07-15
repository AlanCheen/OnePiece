package me.yifeiyuan.onepiece.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.yifeiyuan.onepiece.pandora.ktx.runIfIs
import me.yifeiyuan.onepiece.pandora.ktx.start
import me.yifeiyuan.onepiece.pandora.ktx.startNewThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val s :String ="1"
        s.runIfIs<AppCompatActivity> {

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

        startNewThread {

        }
    }


    private fun testFunction(func:Function0<String>){
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