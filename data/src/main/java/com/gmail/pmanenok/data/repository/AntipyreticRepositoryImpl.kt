package com.gmail.pmanenok.data.repository

import com.gmail.pmanenok.data.entity.antipyretics.Ibuprophenum
import com.gmail.pmanenok.data.entity.antipyretics.Paracetamolum
import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.AntipyreticType
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.repository.DrugRepository
import io.reactivex.Flowable

class AntipyreticRepositoryImpl : DrugRepository {
    private val antipyretics = arrayListOf(Paracetamolum, Ibuprophenum)

    override fun getDrug(type: TypedEnum): Flowable<BaseDrug> {
        return Flowable.fromArray(antipyretics[type.getId()])
    }

    override fun getDrugTypesList(): Flowable<Array<TypedEnum>> {
        return Flowable.fromArray(AntipyreticType.values()).map {
            val test = mutableListOf<TypedEnum>()
            test.addAll(it)
            return@map test.toTypedArray()
        }
    }
}