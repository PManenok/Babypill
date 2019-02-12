package com.gmail.pmanenok.data.entity.antipyretics

import com.gmail.pmanenok.domain.entity.drug.BaseAntipyro
import com.gmail.pmanenok.domain.entity.drug.ConsistValues
import com.gmail.pmanenok.domain.entity.types.SubstanceType
import com.gmail.pmanenok.domain.entity.types.AntipyreticType


object Ibuprophenum : BaseAntipyro {
    override val type: AntipyreticType = AntipyreticType.IBUPROPHENUM
    override val basicDoseInd: Int = 0
    override val dosesList = arrayOf(5, 10)
    override val consist = arrayOf(
        ConsistValues(listOf(60f), 1, 4, SubstanceType.SUPPOSITORY),
        ConsistValues(listOf(100f), 5, 4, SubstanceType.SYRUP),
        ConsistValues(listOf(200f), 5, 4, SubstanceType.SYRUP),
        ConsistValues(listOf(200f), 1, 4, SubstanceType.PILL)
    )
}