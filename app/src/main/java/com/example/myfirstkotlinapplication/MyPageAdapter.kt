package com.example.myfirstkotlinapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                FragmentAndroidVersionInfo()
            }
            else -> {
                return FragmentDeviceInfo()
            }
        }

    }

    override fun getCount(): Int {
       return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position)
        {
            0 -> "AndroidVersionInfo"
            else -> return "DeviceInfo"
        }
        //return super.getPageTitle(position)
    }
}