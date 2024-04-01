package com.gmail.pmanenok.antibiocalc.presentation.screens.main.recycler

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseItemViewModel
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.subscribeBy

class TypedEnumViewModel(private val tapChecker: BehaviorProcessor<Int>?) : BaseItemViewModel<TypedEnum>() {
    val itemName = ObservableInt(0)
    val drugType = ObservableInt(0)
    val item = ObservableField<TypedEnum>()
    val taped = ObservableBoolean(false)

    override fun bindItem(item: TypedEnum, position: Int) {
        taped.set(false)
        this.position.set(position)
        drugType.set(item.getTypeId())
        itemName.set(item.getId())
        this.item.set(item)
        disposable = tapChecker?.subscribeBy(
            onNext = {
                if (itemName.get() == it)
                    taped.set(true)
                else
                    taped.set(false)
            },
            onError = {
                Log.e("TypedEnumViewModel", it.message, it)
            }
        )
    }

    override fun onItemClick() {
        super.onItemClick()
        tapChecker?.offer(itemName.get())
    }
}