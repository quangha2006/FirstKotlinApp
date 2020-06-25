package com.example.myfirstkotlinapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.Toast
import com.example.myfirstkotlinapplication.MainActivity.Companion.LogTag
import com.example.myfirstkotlinapplication.databinding.FragmentAndroidversioninfoBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.File

class FragmentAndroidVersionInfo : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private lateinit var mArrayList: ArrayList<AndroidVersion>
    private var mAdapter: DataAdapterAndroidVersion? = null
    private val mBASEURL = "http://qhcloud.ddns.net/"
    private var mJsonResponse: JSONResponse? = null
    private lateinit var mContext: Context
    private lateinit var mBinding: FragmentAndroidversioninfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        mBinding = FragmentAndroidversioninfoBinding.inflate(inflater)

        mContext = container?.context!!

        initViews()

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        mRecyclerView!!.adapter = null
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        search(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
    private fun initViews() {
        mRecyclerView = mBinding.cardRecycleView
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.addItemDecoration(
            DividerItemDecoration(
                mContext,
                LinearLayoutManager.VERTICAL
            )
        )
        mRecyclerView!!.layoutManager = LinearLayoutManager(mContext)
    }

    private fun loadJSON() {
        val retrofit = Retrofit.Builder()
            .baseUrl(mBASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = retrofit.create(RequestInterface::class.java)
        val call = request.getJSON()
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                if (response.isSuccessful) {
                    val timeResponse = response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis()
                    Log.i(LogTag, "Request response time: " + timeResponse + "ms")
                    val myToast = Toast.makeText(
                        mContext,"Request response time: " + timeResponse + "ms",
                        Toast.LENGTH_LONG)
                    myToast.show()

                    mJsonResponse = response.body()
                    mArrayList = ArrayList(mJsonResponse!!.android)
                    mAdapter = DataAdapterAndroidVersion(mArrayList)
                    Log.i(LogTag, "File version: " + mJsonResponse!!.version)
                    setAdapter()
                } else {
                    Log.e(LogTag, "Request URL: " + response.raw().request().url().toString() + " code: " + response.code())
                }
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.e(LogTag, t.message)
            }
        })
    }
    private fun setAdapter()
    {
        if (mJsonResponse == null){
            loadJSON()
        }
        else {
            mRecyclerView!!.adapter = mAdapter
        }
    }
    private fun search(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                mAdapter?.filter?.filter(newText)
                return true
            }
        })
    }
    private fun WriteJsonToFile(filename:String, json:JSONResponse){
        var file = File(filename)

        //Convert the Json object to JsonString
        var jsonString:String = Gson().toJson(json)

        Log.i(LogTag, jsonString)
        //file.writeText(jsonString)
    }
    data class AndroidVersion (
        var ver: String? = null,
        var name: String? = null,
        var api: String? = null,
        var releasedate: String? = null){

    }
    data class JSONResponse(var version: Int? = null,
                            var android: ArrayList<AndroidVersion>? = null) {

    }
    interface RequestInterface {
        @GET("api/androidversion")
        fun getJSON(): Call<JSONResponse>
    }
}
