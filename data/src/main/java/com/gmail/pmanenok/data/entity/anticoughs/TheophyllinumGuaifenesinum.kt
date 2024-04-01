package com.gmail.pmanenok.data.entity.anticoughs

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.drug.BaseAnticough
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.AnticoughType
import com.gmail.pmanenok.domain.entity.types.SubstanceType


object TheophyllinumGuaifenesinum : BaseAnticough {
    override val type: AnticoughType = AnticoughType.AMBROXOL//THEOPHYLLINUM_GUAIFENESINUM
    override val basicDoseInd: Int = 0
    override val dosesList: Array<Float> = arrayOf(11f, 16.5f)
    private val overWeight: Pair<Float, Float> = Pair(5f, 10f)
    override val ageIsMainValue: Boolean = false
    override val agesList: Array<Int> = emptyArray()
    override val consist = arrayOf(
        ConsistValues(listOf(50f, 30f), 5, 3, SubstanceType.SYRUP)
    )

    override fun getResults(weight: Int, dosageIndex: Int): List<Result> {
        val list = mutableListOf<Result>()
        for (element in consist) {
            val item = Result()
            item.doubble = element.amount.size > 1
            item.drugTypeId = type.getTypeId()
            item.drugSubtypeId = type.getId()
            item.substanceType = element.subType
            val results: Pair<Float, Float> = if (weight >= 40) overWeight
            else countResult(weight, element)
            item.result = results.first
            item.resultMax = results.second
            item.concentration1 = element.amount[0]
            item.concentration2 = element.amount[1]
            item.concentrationIn = element.inSub
            item.times = element.times
            extraAssigning(item, weight, dosageIndex, element)
            list.add(item)
        }
        return list
    }

    private fun countResult(weight: Int, element: ConsistValues): Pair<Float, Float> {
        val preResult = weight.toFloat() * element.inSub / element.amount[0] / element.times
        val minValue = roundSuspension(preResult * dosesList[0])
        val maxValue = roundSuspension(preResult * dosesList[1])
        return Pair(minValue, maxValue)
    }

    override fun getDose(value: Int): String {
        return floatToString(dosesList[0]) + "-" + floatToString(dosesList[1])
    }

    private fun roundSuspension(result: Float): Float {
        return (result * 100).toInt() / 100f
    }

    private fun floatToString(value: Float): String {
        return when {
            (value % 1) == 0f -> value.toInt().toString()
            (value * 10 % 1) == 0f -> (((value * 10f).toInt()) / 10f).toString()
            else -> value.toString()
        }
    }
}