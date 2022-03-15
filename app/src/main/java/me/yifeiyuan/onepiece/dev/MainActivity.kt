package me.yifeiyuan.onepiece.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.yifeiyuan.onepiece.pandora.ktx.copyTextToClipboard
import me.yifeiyuan.onepiece.pandora.ktx.start

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun testContext() {
        start<MainActivity>()
    }
}