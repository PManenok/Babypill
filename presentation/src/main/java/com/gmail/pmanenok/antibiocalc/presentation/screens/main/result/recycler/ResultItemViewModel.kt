package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.recycler

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseItemViewModel
import com.gmail.pmanenok.antibiocalc.presentation.utils.roundFloat
import com.gmail.pmanenok.domain.entity.Result
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
        if (item.result == item.resultMax || item.resultMax == 0f) resultText.set(roundFloat(item.result))
        else resultText.set(roundFloat(item.result) + "-" + roundFloat(item.resultMax))
        this.times.set(item.times)
        this.doubble.set(item.doubble)
        if (item.doubble)
            this.concentrationText.set(roundFloat(item.concentration1) + "/" + roundFloat(item.concentration2))
        else this.concentrationText.set(roundFloat(item.concentration1))
        this.concentrationIn.set(item.concentrationIn)
    }
}