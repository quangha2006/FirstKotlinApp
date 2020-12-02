package com.example.myfirstkotlinapplication.ui.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myfirstkotlinapplication.ui.home.fragments.FragmentAndroidVersionInfo
import com.example.myfirstkotlinapplication.ui.home.fragments.FragmentDeviceInfo
import com.example.myfirstkotlinapplication.ui.home.fragments.FragmentWakeOnLan

class MyPageAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentWakeOnLan()
            //1 -> FragmentAndroidVersionInfo()
            //2 -> FragmentDeviceInfo()
            else -> return FragmentWakeOnLan()
        }

    }

    override fun getCount(): Int {
        return 1
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "WakeOnLan"
            //1 -> "Android Version Info"
            //2 -> "DeviceInfo"
            else -> return "unknown"
        }
    }
}