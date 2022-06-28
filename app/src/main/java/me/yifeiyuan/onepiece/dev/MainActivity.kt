package me.yifeiyuan.onepiece.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.yifeiyuan.onepiece.foundation.core.Executable
import me.yifeiyuan.onepiece.pandora.ktx.start

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun testContext() {
        start<MainActivity>()
    }

    override fun onResume() {
        super.onResume()

        testExecutable(object:Executable<String>{
            override fun execute(params: String) {
            }
        })
    }

    private fun testExecutable(executable: Executable<String>) {
        executable.execute("test")
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