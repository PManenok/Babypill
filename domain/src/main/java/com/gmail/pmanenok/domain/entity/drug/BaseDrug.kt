package com.gmail.pmanenok.domain.entity.drug

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.types.TypedEnum

interface BaseDrug {
    val type: TypedEnum
    val basicDoseInd: Int
    val dosesList: Array<Float>
    val agesList: Array<Int>
    val consist: Array<ConsistValues>
    val ageIsMainValue: Boolean

    fun getResults(weight: Int, dosageIndex: Int): List<Result>
    fun getResults(age: Int): List<Result>

    fun extraAssigning(item: Result, weight: Int, dosageIndex: Int, element: ConsistValues)
}