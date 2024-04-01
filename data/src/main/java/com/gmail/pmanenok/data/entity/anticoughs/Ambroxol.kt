package com.gmail.pmanenok.data.entity.anticoughs

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.drug.BaseAnticough
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.AnticoughType
import com.gmail.pmanenok.domain.entity.types.SubstanceType


object Ambroxol : BaseAnticough {
    override val type: AnticoughType = AnticoughType.AMBROXOL
    override val basicDoseInd: Int = 0
    override val ageIsMainValue: Boolean = true
    override val dosesList = arrayOf(7.5f, 15f, 30f)
    override val agesList = arrayOf(2, 5, 12)
    override val consist = arrayOf(
        ConsistValues(listOf(15f), 5, 3, SubstanceType.SYRUP),
        ConsistValues(listOf(30f), 5, 3, SubstanceType.SYRUP),
        ConsistValues(listOf(7.5f), 1, 3, SubstanceType.SOLUTION),
        ConsistValues(listOf(30f), 1, 3, SubstanceType.PILL)
    )


    override fun getResults(age: Int): List<Result> {
        val list = mutableListOf<Result>()
        for (element in consist) {
            val item = Result()
            item.doubble = element.amount.size > 1
            item.drugTypeId = type.getTypeId()
            item.drugSubtypeId = type.getId()
            item.substanceType = element.subType
            item.result = countResult(age, element)
            item.concentration1 = element.amount[0]
            item.concentrationIn = element.inSub
            item.times = element.times
            if (item.result > 0f) list.add(item)
        }
        return list
    }

    private fun countResult(age: Int, element: ConsistValues): Float {
        var minValue = 0f
        val dosage = getDosage(age)
        if (dosage != 0f) {
            minValue = getResultValue(element.subType, dosage, element.times, element.amount[0], element.inSub)
        }
        return minValue
    }

    private fun getDosage(age: Int): Float {
        var minAge = agesList[0]
        if (age > agesList[agesList.lastIndex]) return dosesList[dosesList.lastIndex]
        for ((ind, maxAge) in agesList.withIndex()) {
            if (maxAge == minAge) continue
            if (age in (minAge)..(maxAge)) {
                return dosesList[ind - 1]
            } else minAge = maxAge
        }
        return 0f
    }

    private fun getResultValue(subtype: SubstanceType, dosage: Float, times: Int, amount: Float, inSub:Int): Float {
        val result = dosage / amount * inSub
        return when (subtype) {
            SubstanceType.PILL -> {
                roundPill(result)
            }
            else -> {
                roundSuspension(result)
            }
        }
    }

    override fun getDose(value: Int): String {
        val dosage: Float = getDosage(value)
        if (dosage != 0f) {
            return floatToString(dosage)
        }
        return ""
    }

    private fun roundSuspension(result: Float): Float {
        return (result * 100).toInt() / 100f
    }

    private fun roundPill(result: Float): Float {
        val remain = result % 1
        return if (remain >= 0.5f) result - remain + 0.5f
        else result - remain
    }

    private fun floatToString(value: Float): String {
        return when {
            (value % 1) == 0f -> value.toInt().toString()
            (value * 10 % 1) == 0f -> (((value * 10f).toInt()) / 10f).toString()
            else -> value.toString()
        }
    }
}