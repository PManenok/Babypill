package com.gmail.pmanenok.data.entity.antibiotics

import com.gmail.pmanenok.domain.entity.drug.BaseAntibio
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntibioticType


object Amoxicillin : BaseAntibio {
    override val type: AntibioticType = AntibioticType.AMOXICILLIN
    override val basicDoseInd: Int = 1
    override val dosesList = arrayOf(30, 50, 90)
    override val consist = arrayOf(
        ConsistValues(listOf(125f), 5, 3, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f), 5, 3, SubstanceType.SUSPENSION),
        ConsistValues(listOf(500f), 1, 3, SubstanceType.PILL),
        ConsistValues(listOf(1000f), 1, 2, SubstanceType.PILL)
    )
}