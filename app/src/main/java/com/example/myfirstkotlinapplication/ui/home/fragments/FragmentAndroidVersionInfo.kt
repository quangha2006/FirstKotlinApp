package com.example.myfirstkotlinapplication.ui.home.fragments

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstkotlinapplication.R
import com.example.myfirstkotlinapplication.base.BaseFragment
import com.example.myfirstkotlinapplication.databinding.FragmentAndroidversioninfoBinding
import com.example.myfirstkotlinapplication.ui.home.MainViewModel
import com.example.myfirstkotlinapplication.ui.home.adapters.DataAdapterAndroidVersion
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FragmentAndroidVersionInfo : BaseFragment<FragmentAndroidversioninfoBinding>() {

    private var dataAdapterAndroidVersion: DataAdapterAndroidVersion? = null
    private val viewModel: MainViewModel by sharedViewModel()

    override fun bindingView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAndroidversioninfoBinding.inflate(inflater, container, false)

    override fun initUIComponent() {
        setHasOptionsMenu(true)

        dataAdapterAndroidVersion = DataAdapterAndroidVersion(arrayListOf())
        binding.cardRecycleView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context, LinearLayoutManager.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapterAndroidVersion
        }

        viewModel.getAndroidVersionInfo()
    }

    override fun observerViewModel() {
        viewModel.androidVersionInfo.observe(this, Observer { verInfo ->
            verInfo.android?.let { androidList ->
                dataAdapterAndroidVersion?.setNewData(androidList)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        search(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun search(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                dataAdapterAndroidVersion?.filter?.filter(newText)
                return true
            }
        })
    }
}
