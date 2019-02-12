package com.gmail.pmanenok.domain.entity.drug

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.types.AntipyreticType
import com.gmail.pmanenok.domain.entity.types.SubstanceType

interface BaseAntipyro : BaseDrug {
    override val type: AntipyreticType

    override fun getResults(weight: Int, dosageIndex: Int): List<Result> {
        val list = mutableListOf<Result>()
        for (element in consist) {
            if ((weight > 20 && element.subType == SubstanceType.SYRUP)
                || (weight < 20 && element.subType == SubstanceType.PILL)) {
                continue
            }
            val item = Result()
            item.doubble = element.amount.size > 1
            item.drugTypeId = type.getTypeId()
            item.drugSubtypeId = type.getId()
            item.substanceType = element.subType
            val results: Pair<Float, Float> = countResult(weight, element)
            item.result = results.first
            item.resultMax = results.second
            item.concentration1 = element.amount[0]
            item.concentrationIn = element.inSub
            item.times = element.times
            extraAssigning(item, weight, dosageIndex, element)
            if (item.result > 0f) list.add(item)
        }
        return list
    }

    fun countResult(weight: Int, element: ConsistValues): Pair<Float, Float> {
        var minValue = 0f
        var maxValue = 0f
        if (element.subType == SubstanceType.SYRUP || element.subType == SubstanceType.SUSPENSION) {
            minValue = roundSuspension(weight.toFloat() * dosesList[0] * element.inSub / element.amount[0])
            maxValue = roundSuspension(weight.toFloat() * dosesList[1] * element.inSub / element.amount[0])
        } else if (element.subType == SubstanceType.SUPPOSITORY) {
            val minBorder = weight.toFloat() * dosesList[0]
            val maxBorder = weight.toFloat() * dosesList[1]
            if (element.amount[0] in minBorder..maxBorder) {
                minValue = 1f
                maxValue = minValue
            }
        } else {
            minValue = roundPill(weight.toFloat() * dosesList[0] / element.amount[0])
            maxValue = roundPill(weight.toFloat() * dosesList[1] / element.amount[0])
        }
        return Pair(minValue, maxValue)
    }

    private fun roundSuspension(result: Float): Float {
        return (result * 100).toInt() / 100f
    }

    private fun roundPill(result: Float): Float {
        val remain = result % 1
        return if (remain >= 0.5f) result - remain + 0.5f
        else result - remain
    }

    override fun extraAssigning(item: Result, weight: Int, dosageIndex: Int, element: ConsistValues) {
    }
}