package com.example.myfirstkotlinapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.android.synthetic.*

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = this.bindingView(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIComponent()
        observerViewModel()
    }

    abstract fun bindingView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    abstract fun initUIComponent()

    abstract fun observerViewModel()

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    override fun onDestroyView() {
        this.clearFindViewByIdCache()
        super.onDestroyView()
    }

}