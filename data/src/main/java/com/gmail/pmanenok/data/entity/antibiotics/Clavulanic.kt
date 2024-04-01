package com.gmail.pmanenok.data.entity.antibiotics

import com.gmail.pmanenok.domain.entity.Result
import com.gmail.pmanenok.domain.entity.drug.BaseAntibio
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntibioticType

object Clavulanic : BaseAntibio {
    override val type: AntibioticType = AntibioticType.CLAVULANIC
    override val basicDoseInd: Int = 2
    override val dosesList = arrayOf(30f, 40f, 50f, 60f, 70f, 80f, 90f)
    override val agesList: Array<Int> = emptyArray()
    override val ageIsMainValue: Boolean=false
    override val consist = arrayOf(
        ConsistValues(listOf(125f, 31.25f), 5, 3, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f, 62.5f), 5, 3, SubstanceType.SUSPENSION),
        ConsistValues(listOf(400f, 57.1f), 5, 2, SubstanceType.SUSPENSION),
        ConsistValues(listOf(500f, 125f), 1, 3, SubstanceType.PILL),
        ConsistValues(listOf(875f, 125f), 1, 2, SubstanceType.PILL)
    )

    override fun extraAssigning(item: Result, weight: Int, dosageIndex: Int, element: ConsistValues) {
        super.extraAssigning(item, weight, dosageIndex, element)
        item.concentration2 = element.amount[1]
    }
}