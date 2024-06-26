package com.gmail.pmanenok.data.entity.antibiotics

import com.gmail.pmanenok.domain.entity.drug.BaseAntibio
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntibioticType

object Cefdinir : BaseAntibio {
    override val type: AntibioticType = AntibioticType.CEFDINIR
    override val basicDoseInd: Int = 0
    override val dosesList = arrayOf(14f)
    override val agesList: Array<Int> = emptyArray()
    override val ageIsMainValue: Boolean=false
    override val consist = arrayOf(
        ConsistValues(listOf(125f), 5, 1, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f), 5, 1, SubstanceType.SUSPENSION),
        ConsistValues(listOf(125f), 5, 2, SubstanceType.SUSPENSION),
        ConsistValues(listOf(250f), 5, 2, SubstanceType.SUSPENSION),
        ConsistValues(listOf(300f), 1, 1, SubstanceType.PILL)
    )
}