package com.gmail.pmanenok.data.entity.antibiotics

import com.gmail.pmanenok.domain.entity.drug.BaseAntibio
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntibioticType

object Cefuroximum : BaseAntibio {
    override val type: AntibioticType = AntibioticType.CEFUROXIMUM
    override val basicDoseInd: Int = 0
    override val dosesList = arrayOf(10, 15)
    override val consist = arrayOf(
        ConsistValues(listOf(125f), 5, 2, SubstanceType.SUSPENSION),
        ConsistValues(listOf(125f), 1, 2, SubstanceType.PILL),
        ConsistValues(listOf(250f), 1, 2, SubstanceType.PILL),
        ConsistValues(listOf(500f), 1, 2, SubstanceType.PILL)
    )
}