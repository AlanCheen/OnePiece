package me.yifeiyuan.onepiece.foundation

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by 程序亦非猿 on 2021/3/24.
 */
open class BaseActivity : AppCompatActivity {

    constructor() : super() {}

    constructor(contentLayoutId: Int) : super(contentLayoutId) {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}