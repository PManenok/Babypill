package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.recycler

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseItemViewModel
import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.SubstanceType

class ResultItemViewModel : BaseItemViewModel<Result>() {
    val drugType = ObservableInt(0)
    val drugSubtype = ObservableInt(0)
    val substanceType = ObservableField<SubstanceType>(SubstanceType.PILL)
    val resultText = ObservableField<String>("")
    val times = ObservableInt(0)
    val concentrationText = ObservableField<String>("")
    val concentrationIn = ObservableInt(0)
    val doubble = ObservableBoolean(false)

    override fun bindItem(item: Result, position: Int) {
        this.position.set(position)
        drugType.set(item.drugTypeId)
        drugSubtype.set(item.drugSubtypeId)
        substanceType.set(item.substanceType)
        if ((item.drugTypeId == MenuType.ANTIBIOTIC.getId())
            || (item.drugTypeId == MenuType.ANTIPYRETIC.getId() && (item.result == item.resultMax || item.resultMax == 0f))
        ) {
            resultText.set(roundFloat(item.result))
        } else if (item.drugTypeId == MenuType.ANTIPYRETIC.getId() && item.result != item.resultMax)
            resultText.set(roundFloat(item.result) + "-" + roundFloat(item.resultMax))
        this.times.set(item.times)
        this.doubble.set(item.doubble)
        if (item.doubble)
            this.concentrationText.set(roundFloat(item.concentration1) + "/" + roundFloat(item.concentration2))
        else this.concentrationText.set(roundFloat(item.concentration1))
        this.concentrationIn.set(item.concentrationIn)
    }

    fun roundFloat(value: Float): String {
        return when {
            (value % 1) == 0f -> value.toInt().toString()
            (value * 10 % 1) == 0f -> (((value * 10f).toInt()) / 10f).toString()
            else -> value.toString()
        }
    }
}