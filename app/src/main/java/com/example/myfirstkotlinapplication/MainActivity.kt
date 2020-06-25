package com.example.myfirstkotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstkotlinapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        var LogTag = "QUANGHA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)

        setContentView(binding.root)

        //===================Fragment=====================================
        val fragmentAdapter = MyPageAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
        //================================================================
    }
}
//tutorial
//https://www.learn2crack.com/2017/03/searchview-with-recyclerview.html