package com.example.myfirstkotlinapplication.ui.home

import android.view.LayoutInflater
import com.example.myfirstkotlinapplication.base.BaseActivity
import com.example.myfirstkotlinapplication.databinding.ActivityMainBinding
import com.example.myfirstkotlinapplication.ui.home.adapters.MyPageAdapter


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {

    override fun bindingView(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)

    override fun initUIComponent() {
        setSupportActionBar(binding.toolbar)

        val fragmentAdapter =
            MyPageAdapter(
                supportFragmentManager
            )
        binding.viewPager.apply {
            offscreenPageLimit = 3
            adapter = fragmentAdapter
        }
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }

    override fun observerViewModel() {

    }

    companion object {
        var LogTag = "QUANGHA"
    }

}
