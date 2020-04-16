package com.example.myfirstkotlinapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_android_version_info.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * A simple [Fragment] subclass.
 */
class FragmentAndroidVersionInfo : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mArrayList: ArrayList<AndroidVersion>
    private var mAdapter: DataAdapter? = null
    private val BASE_URL = "http://qhcloud.ddns.net/"
    private val LOGTAG = "QUANGHA"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view:View = inflater.inflate(R.layout.fragment_android_version_info, container, false)

        if (container != null) {
            initViews(container.context, view)
            loadJSON(container.context)
        }

        return view
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
    private fun initViews(context: Context, view:View) {
        mRecyclerView = view.card_recycler_view
        mRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = layoutManager

    }
    private fun loadJSON(context:Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = retrofit.create(RequestInterface::class.java)
        val call = request.getJSON()
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                if (response.isSuccessful) {
                    val timeResponse = response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis()
                    Log.i(LOGTAG, "Request response time: " + timeResponse + "ms")
                    val myToast = Toast.makeText(context,"Request response time: " + timeResponse + "ms",
                        Toast.LENGTH_LONG)
                    myToast.show()

                    val jsonResponse = response.body()
                    mArrayList = ArrayList(jsonResponse.getAndroid())
                    mAdapter = DataAdapter(mArrayList)
                    mRecyclerView.adapter = mAdapter
                } else {
                    Log.e(LOGTAG, "Request URL: " + response.raw().request().url().toString() + " code: " + response.code())
                }
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.e(LOGTAG, t.message)
            }
        })
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
}
