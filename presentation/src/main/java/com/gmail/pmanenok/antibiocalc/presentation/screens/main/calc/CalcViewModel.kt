package com.gmail.pmanenok.antibiocalc.presentation.screens.main.calc

import android.view.View
import android.widget.SeekBar
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.recycler.TypedEnumAdapter
import com.gmail.pmanenok.antibiocalc.presentation.utils.roundFloat
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

    private val maxAge = 17
    private val maxWeight = 40

    lateinit var drugType: MenuType
    private var currentDrug: BaseDrug? = null
    private var isWeight = false
    private var dosesCount = 0
    private var dosageInd = 0

    val maxValue = ObservableInt(0)

    val dosageText = ObservableField<String>("")
    val dosageProgress = ObservableInt(0)
    val dosageVisibility = ObservableBoolean(false)
    val dosageTextVisibility = ObservableBoolean(false)

    val value = ObservableInt(0)
    val valueText = ObservableField<String>("")
    val valueVisibility = ObservableBoolean(false)

    val onResultClick = View.OnClickListener {
        val drug = currentDrug
        if (drug != null) {
            adapter.clearDisposables()
            if (isWeight) router?.goToResultFragment(drugType, drug.type, value.get() + 1, dosageInd, isWeight)
            else router?.goToResultFragment(drugType, drug.type, value.get() + 2, dosageInd, isWeight)
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
                val oldValue=isWeight
                isWeight = !drug.ageIsMainValue
                if (oldValue!=isWeight){
                    value.set(0)
                }
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
            },
            onError = {
                router?.showError(it)
            }
        ))
    }

    fun onValueProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (isWeight) valueText.set(router?.getWeightString(if (progress == seekBar.max) "$progress>" else (progress + 1).toString()))
        else valueText.set(router?.getAgeString(if (progress == seekBar.max) "${progress + 1}>" else (progress + 2).toString()))
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
        dosageProgress.set((dosageInd) * seekBar.max / dosesCount)
    }

    private fun updateValues() {
        if (currentDrug != null) {
            if (isWeight) {
                dosageVisibility()
                maxValue.set(maxWeight)
                valueText.set(router?.getWeightString(if (value.get() == maxWeight) "${value.get()}>" else (value.get() + 1).toString()))
                valueVisibility.set(true)
            } else {
                dosageVisibility.set(false)
                dosageTextVisibility.set(false)
                maxValue.set(maxAge)
                valueText.set(router?.getAgeString(if (value.get() == maxAge) "${value.get() + 1}>" else (value.get() + 2).toString()))
                valueVisibility.set(true)
            }
        } else {
            router?.showMessage(router?.getResourceString(R.string.error_drug_not_choosed) ?: "Nothing was choosed")
        }
    }

    private fun dosageVisibility() {
        if (currentDrug!!.type.getTypeId() == MenuType.ANTIPYRETIC.getId() || currentDrug!!.type.getTypeId() == MenuType.ANTICOUGH.getId()) {
            dosageText.set(router?.getDayDosageString(roundFloat(currentDrug!!.dosesList[0])+"-"+roundFloat(currentDrug!!.dosesList[1])))
            dosageVisibility.set(false)
        } else if (currentDrug!!.dosesList.size == 1) {
            dosageInd = 0
            dosageText.set(router?.getDosageString(roundFloat(currentDrug!!.dosesList[0])))
            dosageVisibility.set(false)
        } else {
            dosageInd = currentDrug!!.basicDoseInd
            dosageText.set(router?.getDosageString(roundFloat(currentDrug!!.dosesList[dosageInd])))
            dosesCount = currentDrug!!.dosesList.size - 1
            dosageProgress.set(dosageInd * 100 / dosesCount)
            dosageVisibility.set(true)
        }
        dosageTextVisibility.set(true)
    }
}
