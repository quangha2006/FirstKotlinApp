package com.example.myfirstkotlinapplication.ui.home.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myfirstkotlinapplication.base.BaseFragment
import com.example.myfirstkotlinapplication.databinding.FragmentDeviceinfoBinding


class FragmentDeviceInfo : BaseFragment<FragmentDeviceinfoBinding>() {
    override fun bindingView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDeviceinfoBinding.inflate(inflater, container, false)

    override fun initUIComponent() {

    }

    override fun observerViewModel() {

    }


}
