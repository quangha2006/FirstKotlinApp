package com.example.myfirstkotlinapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class FragmentDeviceInfo : Fragment() {

    private val LOGTAG="QUANGHA"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        Log.i(LOGTAG, "FragmentDeviceInfo onCreateView")
        return inflater.inflate(R.layout.fragment_device_info, container, false)
    }

}
