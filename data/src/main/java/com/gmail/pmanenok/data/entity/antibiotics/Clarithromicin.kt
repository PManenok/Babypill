package com.gmail.pmanenok.data.entity.antibiotics

import com.gmail.pmanenok.domain.entity.drug.BaseAntibio
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntibioticType

object Clarithromicin : BaseAntibio {
    override val type: AntibioticType = AntibioticType.CLARITHROMICIN
    override val basicDoseInd: Int = 0
    override val dosesList = arrayOf(15f)
    override val agesList: Array<Int> = emptyArray()
    override val ageIsMainValue: Boolean=false
    override val consist = arrayOf(
        ConsistValues(listOf(125f), 5, 2, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f), 5, 2, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f), 1, 2, SubstanceType.PILL),
        ConsistValues(listOf(500f), 1, 1, SubstanceType.PILL)
    )
}