package me.yifeiyuan.onepiece.dev

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yifeiyuan.onepiece.pandora.ktx.*

/**
 */
class SpannableStringBuilderDSL : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_s_s_b_d_s_l, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        "SpannableStringBuilderDSL test case".toSpannableDSL {
            bold(0,"SpannableStringBuilderDSL")
            foregroundColor(Color.RED,10,15)
            foregroundColor(Color.RED,"case")
            backgroundColor(Color.BLUE,"test")
            italic(0,"able")
        }.bindTo(view.findViewById(R.id.result))
    }

}