package com.gmail.pmanenok.domain.repository

import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import io.reactivex.Flowable

interface DrugRepository : BaseRepository{
    fun getDrug(type: TypedEnum): Flowable<BaseDrug>
    fun getDrugTypesList(): Flowable<Array<TypedEnum>>
}