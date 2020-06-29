package com.example.myfirstkotlinapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewBinding, out M : ViewModel>(clazz: KClass<M>) :
    AppCompatActivity() {

    val viewModel: M by lazy { getViewModel(clazz) }

    lateinit var binding: T

    abstract fun observerViewModel()

    abstract fun initUIComponent()

    abstract fun bindingView(
        inflater: LayoutInflater
    ): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = this.bindingView(layoutInflater)
        setContentView(binding.root)
        observerViewModel()
        initUIComponent()
    }
}