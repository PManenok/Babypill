package com.gmail.pmanenok.data.entity.anticoughs

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.drug.BaseAnticough
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.AnticoughType
import com.gmail.pmanenok.domain.entity.types.SubstanceType


object Acetylcysteine : BaseAnticough {
    private val ageDoses: List<List<Int>> =
        arrayListOf(arrayListOf(200, 300), arrayListOf(300, 400), arrayListOf(400, 600))
    override val type: AnticoughType = AnticoughType.ACETYLCYSTEINE
    override val basicDoseInd: Int = 0
    override val ageIsMainValue: Boolean = true
    override val dosesList = emptyArray<Float>()
    override val agesList = arrayOf(2, 6, 14)
    override val consist = arrayOf(
        ConsistValues(listOf(20f), 1, 3, SubstanceType.SYRUP),
        ConsistValues(listOf(20f), 1, 2, SubstanceType.SYRUP),
        ConsistValues(listOf(100f), 1, 2, SubstanceType.POWDER),
        ConsistValues(listOf(100f), 1, 3, SubstanceType.POWDER),
        ConsistValues(listOf(200f), 1, 2, SubstanceType.POWDER),
        ConsistValues(listOf(200f), 1, 3, SubstanceType.POWDER),
        ConsistValues(listOf(600f), 1, 2, SubstanceType.POWDER),
        ConsistValues(listOf(600f), 1, 3, SubstanceType.POWDER),
        ConsistValues(listOf(100f), 1, 2, SubstanceType.PILL),
        ConsistValues(listOf(100f), 1, 3, SubstanceType.PILL),
        ConsistValues(listOf(200f), 1, 2, SubstanceType.PILL),
        ConsistValues(listOf(200f), 1, 3, SubstanceType.PILL),
        ConsistValues(listOf(600f), 1, 2, SubstanceType.PILL),
        ConsistValues(listOf(600f), 1, 3, SubstanceType.PILL)
    )

    override fun getResults(age: Int): List<Result> {
        val list = mutableListOf<Result>()
        for (element in consist) {
            val item = Result()
            item.doubble = element.amount.size > 1
            item.drugTypeId = type.getTypeId()
            item.drugSubtypeId = type.getId()
            item.substanceType = element.subType
            val results: Pair<Float, Float> = countResult(age, element)
            item.result = results.first
            item.resultMax = results.second
            item.concentration1 = element.amount[0]
            item.concentrationIn = element.inSub
            item.times = element.times
            if (item.result > 0f) list.add(item)
        }
        return list
    }

    private fun countResult(age: Int, element: ConsistValues): Pair<Float, Float> {
        var minValue = 0f
        var maxValue = 0f
        val dosage: List<Int> = getDosage(age)
        if (dosage.isNotEmpty()) {
            minValue = getResultValue(element.subType, dosage[0], element.times, element.amount[0])
            maxValue = getResultValue(element.subType, dosage[1], element.times, element.amount[0])
        }
        return Pair(minValue, maxValue)
    }

    private fun getDosage(age: Int): List<Int> {
        var minAge = agesList[0]
        if (age > agesList[agesList.lastIndex]) return ageDoses[ageDoses.lastIndex]
        else {
            for ((ind, maxAge) in agesList.withIndex()) {
                if (maxAge == minAge) continue
                else if (age in minAge..maxAge) {
                    return ageDoses[ind - 1]
                } else minAge = maxAge
            }
        }
        return emptyList()
    }

    private fun getResultValue(subtype: SubstanceType, dosage: Int, times: Int, amount: Float): Float {
        val result = dosage.toFloat() / times / amount
        return if (subtype == SubstanceType.POWDER || subtype == SubstanceType.PILL) {
            roundPill(result)
        } else {
            roundSuspension(result)
        }

    }

    override fun getDose(value: Int): String {
        val dosage: List<Int> = getDosage(value)
        if (dosage.isNotEmpty()) {
            return "${dosage[0]}-${dosage[1]}"
        }
        return ""
    }

    private fun roundSuspension(result: Float): Float {
        return (result * 100).toInt() / 100f
    }

    private fun roundPill(result: Float): Float {
        val remain = result % 1
        return result - remain
    }
}