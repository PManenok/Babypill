package com.gmail.pmanenok.data.entity.antibiotics

import com.gmail.pmanenok.domain.entity.drug.BaseAntibio
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.AntibioticType
import com.gmail.pmanenok.domain.entity.types.SubstanceType


object Amoxicillin : BaseAntibio {
    override val type: AntibioticType = AntibioticType.AMOXICILLIN
    override val basicDoseInd: Int = 2
    override val dosesList = arrayOf(30f, 40f, 50f, 60f, 70f, 80f, 90f)
    override val ageIsMainValue: Boolean=false
    override val agesList: Array<Int> = emptyArray()
    override val consist = arrayOf(
        ConsistValues(listOf(125f), 5, 3, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f), 5, 3, SubstanceType.SUSPENSION),
        ConsistValues(listOf(500f), 1, 3, SubstanceType.PILL),
        ConsistValues(listOf(1000f), 1, 2, SubstanceType.PILL)
    )
}