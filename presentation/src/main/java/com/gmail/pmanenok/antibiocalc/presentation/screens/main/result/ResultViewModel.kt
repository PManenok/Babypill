package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result

import android.databinding.ObservableField
import android.view.View
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.recycler.ResultItemAdapter
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
    var weightValue = 0
    var dosageIndex = 0
    lateinit var drugType: MenuType
    lateinit var drugSubtype: TypedEnum

    fun initiateValues() {
        array.clear()
        array.addAll(getStringsValues())
        getDrugUseCase.type = drugType
        addToDisposable(
            getDrugUseCase.getDrug(drugSubtype).subscribeBy(
                onNext = {
                    val substance = router?.getResources()?.getStringArray(R.array.result_substances)
                    val dose: String =
                        if (it.type.getTypeId() == MenuType.ANTIPYRETIC.getId()) "${it.dosesList[0]}-${it.dosesList[1]}"
                        else it.dosesList[dosageIndex].toString()
                    resultText.set(getSubstanceText(substance, dose, it))
                    adapter.addItems(it.getResults(weightValue, dosageIndex))
                },
                onError = {
                    router?.showError(it)
                }
            )
        )
    }

    private fun getSubstanceText(substance: Array<String>?, dose: String, it: BaseDrug): String {
        return if (!substance.isNullOrEmpty()) {
            if (it.type.getTypeId() == MenuType.ANTIPYRETIC.getId())
                router?.getResources()?.getString(
                    R.string.result_antipyro_template, substance[it.type.getTypeId()],
                    array[it.type.getId()],
                    if (weightValue > 40) "${weightValue - 1}>" else weightValue.toString(),
                    dose, "${weightValue * it.dosesList[0]}-${weightValue * it.dosesList[1]}"
                ) ?: ""
            else router?.getResources()?.getString(
                R.string.result_template, substance[it.type.getTypeId()], array[it.type.getId()],
                if (weightValue > 40) "${weightValue - 1}>" else weightValue.toString(), dose
            ) ?: ""
        } else ""
    }

    fun getStringsValues(): Array<String> {
        val stringId = when (drugType) {
            MenuType.ANTIBIOTIC -> R.array.antibiotics
            MenuType.ANTIPYRETIC -> R.array.antipyretics
        }
        return router?.getResources()?.getStringArray(stringId) ?: emptyArray()
    }
}
