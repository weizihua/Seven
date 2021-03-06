package com.luuu.seven.util

import android.arch.lifecycle.MutableLiveData
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.addTo(cd: CompositeDisposable) {
    cd.add(this)
}

fun <T> ioMain(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> handleLoading(
        showLoading: Boolean,
        load: MutableLiveData<Boolean>,
        isRefresh: Boolean = false,
        refresh: MutableLiveData<Boolean>? = null,
        isLoadMore: Boolean = false,
        loadMore: MutableLiveData<Boolean>? = null): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream.doOnSubscribe {
            if (showLoading) {
                load.value = true
            }
            if (isRefresh) {
                refresh?.value = true
            }
            if (isLoadMore) {
                loadMore?.value = true
            }
        }.doFinally {
            if (showLoading) {
                load.value = false
            }
            if (isRefresh) {
                refresh?.value = false
            }
            if (isLoadMore) {
                loadMore?.value = false
            }
        }
    }
}