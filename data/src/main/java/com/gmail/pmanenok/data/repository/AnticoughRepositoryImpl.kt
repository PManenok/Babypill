package com.gmail.pmanenok.data.repository

import com.gmail.pmanenok.data.entity.anticoughs.Acetylcysteine
import com.gmail.pmanenok.data.entity.anticoughs.Ambroxol
import com.gmail.pmanenok.data.entity.anticoughs.TheophyllinumGuaifenesinum
import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.AnticoughType
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.repository.DrugRepository
import io.reactivex.Flowable

class AnticoughRepositoryImpl : DrugRepository {
    private val anticoughs = arrayListOf(Ambroxol,Acetylcysteine/*,TheophyllinumGuaifenesinum*/)

    override fun getDrug(type: TypedEnum): Flowable<BaseDrug> {
        return Flowable.fromArray(anticoughs[type.getId()])
    }

    override fun getDrugTypesList(): Flowable<Array<TypedEnum>> {
        return Flowable.fromArray(AnticoughType.values()).map {
            val test = mutableListOf<TypedEnum>()
            test.addAll(it)
            return@map test.toTypedArray()
        }
    }
}