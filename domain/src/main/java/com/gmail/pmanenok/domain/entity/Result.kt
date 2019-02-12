package com.gmail.pmanenok.domain.entity

import com.gmail.pmanenok.domain.entity.types.SubstanceType

class Result : Entity {
    var drugTypeId = 0
    var drugSubtypeId = 0
    var substanceType = SubstanceType.SUSPENSION
    var doubble = false
    var concentration1 = 0f
    var concentration2 = 0f
    var concentrationIn = 0
    var times = 0
    var result = 0f
    var resultMax = 0f
    override fun getViewType(): Int {
        return drugTypeId
    }
}