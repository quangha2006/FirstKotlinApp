package com.example.myfirstkotlinapplication

import android.icu.util.Calendar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.nio.file.Paths
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var mytextview = findViewById<TextView>(R.id.usage)
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}
//tutorial
//https://www.learn2crack.com/2017/03/searchview-with-recyclerview.html