package com.example.myfirstkotlinapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstkotlinapplication.data.entity.response.BaseResponse
import com.example.myfirstkotlinapplication.utils.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(), HasDisposableManager {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventError = MutableLiveData<Event<BaseResponse>>()
    val eventFailure = MutableLiveData<Event<Throwable>>()

    override fun addToDisposable(disposable: Disposable) {
        this.compositeDisposable.add(disposable)
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        return compositeDisposable
    }

    override fun dispose() {
        getCompositeDisposable().clear()
    }


    override fun onCleared() {
        this.dispose()
        super.onCleared()
    }

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showError(baseResponse: BaseResponse) {
        eventError.value = Event(baseResponse)
    }

    fun showFailure(throwable: Throwable) {
        eventFailure.value = Event(throwable)
    }

}