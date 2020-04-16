package com.example.myfirstkotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    private val LOGTAG = "QUANGHA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main2)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //===================Fragment=====================================
        val fragmentAdapter = MyPageAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
        //================================================================
    }
}
//tutorial
//https://www.learn2crack.com/2017/03/searchview-with-recyclerview.html