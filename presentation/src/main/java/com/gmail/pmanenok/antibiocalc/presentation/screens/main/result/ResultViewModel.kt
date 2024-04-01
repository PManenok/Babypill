package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result

import android.view.View
import androidx.databinding.ObservableField
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.recycler.ResultItemAdapter
import com.gmail.pmanenok.antibiocalc.presentation.utils.roundFloat
import com.gmail.pmanenok.domain.entity.drug.BaseAnticough
import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.usecase.drug.GetDrugUseCase
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import javax.inject.Inject

class ResultViewModel : BaseViewModel<MainRouter>() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var getDrugUseCase: GetDrugUseCase

    val onReturnClick = View.OnClickListener {
        router?.popBackStack()
    }

    val array: ArrayList<String> = arrayListOf()
    val adapter = ResultItemAdapter()
    val resultText = ObservableField<String>("")
    var isWeight = true
    var value = 0
    var dosageIndex = 0
    lateinit var drugType: MenuType
    lateinit var drugSubtype: TypedEnum

    fun initiateValues() {
        array.clear()
        array.addAll(getStringsValues())
        getDrugUseCase.type = drugType
        addToDisposable(getDrugUseCase.getDrug(drugSubtype).subscribeBy(onNext = {
            val substance = router?.getResources()?.getStringArray(R.array.result_substances)
            dosageIndex
            val dose =
                if (it.type.getTypeId() == MenuType.ANTIPYRETIC.getId()) roundFloat(it.dosesList[0]) + "-" + roundFloat(
                    it.dosesList[1]
                )
                else if (it.type.getTypeId() == MenuType.ANTICOUGH.getId()) {
                    (it as BaseAnticough).getDose(value)
                } else roundFloat(it.dosesList[dosageIndex])
            resultText.set(getSubstanceText(substance, dose, it))
            if (isWeight) adapter.addItems(it.getResults(value, dosageIndex))
            else adapter.addItems(it.getResults(value))
        }, onError = {
            router?.showError(it)
        }))
    }

    private fun getSubstanceText(substance: Array<String>?, dose: String, it: BaseDrug): String {
        return if (!substance.isNullOrEmpty()) {
            val valueNumStr: String
            val valueStr: String
            if (isWeight) {
                valueNumStr = if (value > 40) "${value - 1}>" else value.toString()
                valueStr =
                    router?.getResources()?.getString(R.string.weight_template, valueNumStr) ?: ""
            } else {
                valueNumStr = if (value > 18) "${value - 1}>" else value.toString()
                valueStr =
                    router?.getResources()?.getString(R.string.age_template, valueNumStr) ?: ""
            }
            if (it.type.getTypeId() == MenuType.ANTIPYRETIC.getId()) router?.getResources()
                ?.getString(
                    R.string.result_antipyro_template,
                    substance[it.type.getTypeId()],
                    array[it.type.getId()],
                    if (value > 40) "${value - 1}>" else value.toString(),
                    dose,
                    "${value * it.dosesList[0]}-${value * it.dosesList[1]}"
                ) ?: ""
            else router?.getResources()?.getString(
                R.string.result_template,
                substance[it.type.getTypeId()],
                array[it.type.getId()],
                valueStr,
                dose
            ) ?: ""
        } else ""
    }

    fun getStringsValues(): Array<String> {
        val stringId = when (drugType) {
            MenuType.ANTIBIOTIC -> R.array.antibiotics
            MenuType.ANTIPYRETIC -> R.array.antipyretics
            MenuType.ANTICOUGH -> R.array.anticoughs
        }
        return router?.getResources()?.getStringArray(stringId) ?: emptyArray()
    }
}
