package com.gmail.pmanenok.domain.entity.drug

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.types.AnticoughType

interface BaseAnticough : BaseDrug {
    override val type: AnticoughType

    override fun getResults(age: Int): List<Result> {
        return emptyList()
    }

    override fun getResults(weight: Int, dosageIndex: Int): List<Result> {
        return emptyList()
    }

    override fun extraAssigning(item: Result, weight: Int, dosageIndex: Int, element: ConsistValues) {
    }

    fun getDose(value: Int): String
}