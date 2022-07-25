package me.yifeiyuan.onepiece.dev

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yifeiyuan.onepiece.architecture.core.LiveBroadcastReceiver

private const val TAG = "TestBlankFragment"

class TestBlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receiver = LiveBroadcastReceiver(requireActivity(), viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: LiveBroadcastReceiver $it")
        }

        val intentFilter = IntentFilter()

        receiver.register(intentFilter)

        receiver.send(Intent().apply {

        }
        )

    }
}