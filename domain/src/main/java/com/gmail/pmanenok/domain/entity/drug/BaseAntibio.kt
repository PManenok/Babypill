package com.gmail.pmanenok.domain.entity.drug

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.types.AntibioticType
import com.gmail.pmanenok.domain.entity.types.SubstanceType

interface BaseAntibio : BaseDrug {
    override val type: AntibioticType

    override fun getResults(weight: Int, dosageIndex: Int): List<Result> {
        val list = mutableListOf<Result>()
        for (element in consist) {
            if (weight >= 30 && element.subType == SubstanceType.SUSPENSION && type != AntibioticType.CEFDINIR && dosageIndex > 1) {
                continue
            }
            val item = Result()
            item.doubble = element.amount.size > 1
            item.drugTypeId = type.getTypeId()
            item.drugSubtypeId = type.getId()
            item.substanceType = element.subType
            item.result = countResult(weight, dosageIndex, element)
            item.concentration1 = element.amount[0]
            item.concentrationIn = element.inSub
            item.times = element.times
            extraAssigning(item, weight, dosageIndex, element)
            if ((element.subType == SubstanceType.SUSPENSION && item.result < 10f) || (element.subType == SubstanceType.PILL && item.result > 0f)) {
                list.add(item)
            }
        }
        return list
    }

    override fun getResults(age: Int): List<Result> {
        return emptyList()
    }

    fun countResult(weight: Int, dosageIndex: Int, element: ConsistValues): Float {
        val result =
            weight.toFloat() * dosesList[dosageIndex] * element.inSub / element.amount[0] / element.times
        if (element.subType == SubstanceType.PILL)
            return (result - (result % 1))
        else
            return (result * 100).toInt() / 100f
    }


    override fun extraAssigning(item: Result, weight: Int, dosageIndex: Int, element: ConsistValues) {
    }
}