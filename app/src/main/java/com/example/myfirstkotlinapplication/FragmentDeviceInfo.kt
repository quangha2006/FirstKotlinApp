package com.example.myfirstkotlinapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class FragmentDeviceInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        Log.i(LogTag, "FragmentDeviceInfo onCreateView")
        return inflater.inflate(R.layout.fragment_deviceinfo, container, false)
    }

}
