package com.example.myfirstkotlinapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> FragmentWalkOnLan()
            1 -> FragmentDeviceInfo()
            2 -> FragmentAndroidVersionInfo()
            else -> return FragmentWalkOnLan()
        }

    }

    override fun getCount(): Int {
       return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position)
        {
            0 -> "WalkOnLan"
            1 -> "DeviceInfo"
            2 -> "AndroidVersionInfo"
            else -> return "unknow"
        }
        //return super.getPageTitle(position)
    }
}