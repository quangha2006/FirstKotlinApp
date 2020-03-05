package com.example.myfirstkotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.learn2crack.com"
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mArrayList: ArrayList<AndroidVersion>
    private lateinit var mAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        initViews()
        loadJSON()

    }
    private fun initViews() {
        mRecyclerView = findViewById<RecyclerView>(R.id.card_recycler_view)
        mRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
    }

    private fun loadJSON() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = retrofit.create(RequestInterface::class.java)
        val call = request.getJSON()
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                val jsonResponse = response.body()
                mArrayList = ArrayList<AndroidVersion>(jsonResponse.getAndroid())
                mAdapter = DataAdapter(mArrayList)
                mRecyclerView.setAdapter(mAdapter)
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        search(searchView)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    private fun search(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                mAdapter.getFilter().filter(newText)
                return true
            }
        })
    }
}
//tutorial
//https://www.learn2crack.com/2017/03/searchview-with-recyclerview.html