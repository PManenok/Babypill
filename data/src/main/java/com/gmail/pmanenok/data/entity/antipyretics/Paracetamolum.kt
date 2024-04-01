package com.gmail.pmanenok.data.entity.antipyretics

import com.gmail.pmanenok.domain.entity.drug.BaseAntipyro
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntipyreticType


object Paracetamolum : BaseAntipyro {
    override val type: AntipyreticType = AntipyreticType.PARACETAMOLUM
    override val basicDoseInd: Int = 0
    override val dosesList = arrayOf(10f, 15f)
    override val agesList: Array<Int> = emptyArray()
    override val ageIsMainValue: Boolean=false
    override val consist = arrayOf(
        ConsistValues(listOf(120f), 5, 4, SubstanceType.SYRUP),
        ConsistValues(listOf(150f), 5, 4, SubstanceType.SYRUP),
        ConsistValues(listOf(50f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(80f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(100f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(125f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(150f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(170f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(250f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(300f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(200f), 1, 4, SubstanceType.PILL),
        ConsistValues(listOf(500f), 1, 4, SubstanceType.PILL)
    )
}