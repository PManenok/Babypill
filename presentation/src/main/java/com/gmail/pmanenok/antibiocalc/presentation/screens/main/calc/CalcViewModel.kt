package com.gmail.pmanenok.antibiocalc.presentation.screens.main.calc

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import android.widget.SeekBar
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.recycler.TypedEnumAdapter
import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.usecase.drug.GetDrugListUseCase
import com.gmail.pmanenok.domain.usecase.drug.GetDrugUseCase
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class CalcViewModel : BaseViewModel<MainRouter>() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var getDrugListUseCase: GetDrugListUseCase
    @Inject
    lateinit var getDrugUseCase: GetDrugUseCase

    lateinit var drugType: MenuType
    private var currentDrug: BaseDrug? = null
    private var dosesCount = 0
    private var dosageInd = 0
    val visibility = ObservableBoolean(false)
    val textVisibility = ObservableBoolean(false)
    val progress = ObservableInt(0)
    val weight = ObservableInt(0)
    val weightText = ObservableField<String>("")
    val dosageText = ObservableField<String>("")

    val onResultClick = View.OnClickListener {
        val drug = currentDrug
        if (drug != null) {
            adapter.clearDisposables()
            router?.goToResultFragment(drugType, drug.type, weight.get() + 1, dosageInd)
        } else {
            router?.showMessage(router?.getResourceString(R.string.error_drug_not_choosed) ?: "Nothing was choosed")
        }
    }
    val onReturnClick = View.OnClickListener {
        adapter.clearDisposables()
        router?.goBack()
    }

    val adapter = TypedEnumAdapter(BehaviorProcessor.create<Int>(), {
        addToDisposable(getDrugUseCase.getDrug(it).subscribeBy(
            onNext = { drug ->
                currentDrug = drug
                updateValues()
            },
            onError = { error ->
                router?.showError(error)
            }
        ))
    })

    fun initiateValues() {
        getDrugUseCase.type = drugType
        getDrugListUseCase.type = drugType
        addToDisposable(getDrugListUseCase.getDrugTypesList().subscribeBy(
            onNext = {
                adapter.cleanItems()
                adapter.addItems(it.toList())
                if (currentDrug != null) adapter.taped?.offer(currentDrug?.type?.getId())
                weightText.set(router?.getWeightString(if (weight.get() == 40) "${weight.get()}>" else (weight.get() + 1).toString()))
            },
            onError = {
                router?.showError(it)
            }
        ))
    }

    fun onWeightProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        weightText.set(router?.getWeightString(if (progress == seekBar.max) "$progress>" else (progress + 1).toString()))
    }

    fun onDosageProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            val half = seekBar.max / dosesCount / 2
            var ind = progress * dosesCount / seekBar.max
            val currentValue = ind * seekBar.max / dosesCount
            if (progress > currentValue + half) ind++
            else if (progress < currentValue - half) ind--
            dosageInd = ind
        }
        dosageText.set(router?.getDosageString(currentDrug!!.dosesList[dosageInd].toString()))
    }

    fun onDosageStopChanging(seekBar: SeekBar) {
        progress.set((dosageInd) * seekBar.max / dosesCount)
    }

    private fun updateValues() {
        if (currentDrug != null) {
            if (currentDrug!!.type.getTypeId() == MenuType.ANTIPYRETIC.getId()) {
                dosageText.set(router?.getDayDosageString("${currentDrug!!.dosesList[0]}-${currentDrug!!.dosesList[1]}"))
                visibility.set(false)
            } else if (currentDrug!!.dosesList.size == 1) {
                dosageInd = 0
                dosageText.set(router?.getDosageString(currentDrug!!.dosesList[0].toString()))
                visibility.set(false)
            } else {
                dosageInd = currentDrug!!.basicDoseInd
                dosageText.set(router?.getDosageString(currentDrug!!.dosesList[dosageInd].toString()))
                dosesCount = currentDrug!!.dosesList.size - 1
                progress.set(dosageInd * 100 / dosesCount)
                visibility.set(true)
            }
            textVisibility.set(true)
        } else {
            router?.showMessage(router?.getResourceString(R.string.error_drug_not_choosed) ?: "Nothing was choosed")
        }
    }
}