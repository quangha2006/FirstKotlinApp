package com.example.myfirstkotlinapplication.utils.extensions

import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

// region [CompositeDisposable]
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) =
    compositeDisposable.add(this)

// endregion

// region [Completable]
fun applyCompletableIoScheduler(): CompletableTransformer {
    return CompletableTransformer { observable ->
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
// endregion

// region [Observable]
private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler): Observable<T> =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> applyObservableIoScheduler(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable -> observable.applyScheduler(Schedulers.io()) }
}

fun <T> applyFormValidator(debounceTime: Long = 850): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
        observable
            .debounce(debounceTime, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
    }
}
