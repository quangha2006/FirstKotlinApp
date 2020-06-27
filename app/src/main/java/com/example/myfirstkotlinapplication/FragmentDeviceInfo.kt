package com.example.myfirstkotlinapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfirstkotlinapplication.databinding.FragmentDeviceinfoBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentDeviceInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_deviceinfo, container, false)
        val binding = FragmentDeviceinfoBinding.inflate(inflater, container, false)

        return (binding.root)
    }

}
