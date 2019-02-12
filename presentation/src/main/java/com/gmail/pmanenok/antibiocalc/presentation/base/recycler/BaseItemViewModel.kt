package com.gmail.pmanenok.antibiocalc.presentation.base.recycler

import android.databinding.ObservableInt
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseItemViewModel<Entity> {
    var disposable: Disposable? = null
    val position = ObservableInt()
    abstract fun bindItem(item: Entity, position: Int)
    open fun onItemClick() {}

    /*protected fun getDisposable(disposable: Disposable) {
        Log.e("BaseItemViewModel","disposable added")
        compositeDisposable.add(disposable)
    }

    fun clearDisposables() {
        Log.e("BaseItemViewModel","disposable clear")
        compositeDisposable.clear()
    }*/
}