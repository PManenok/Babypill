package com.gmail.pmanenok.domain.entity.drug

import com.gmail.pmanenok.domain.entity.types.SubstanceType

data class ConsistValues(val amount: List<Float>, val inSub: Int, val times: Int, val subType: SubstanceType)