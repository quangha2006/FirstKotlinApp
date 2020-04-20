package com.example.myfirstkotlinapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> FragmentAndroidVersionInfo()
            1 -> FragmentDeviceInfo()
            2 -> FragmentWalkOnLan()
            else -> return FragmentWalkOnLan()
        }

    }

    override fun getCount(): Int {
       return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position)
        {
            0 -> "AndroidVersionInfo"
            1 -> "DeviceInfo"
            2 -> "WalkOnLan"
            else -> return "unknow"
        }
        //return super.getPageTitle(position)
    }
}