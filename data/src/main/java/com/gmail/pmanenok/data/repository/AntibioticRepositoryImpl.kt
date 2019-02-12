package com.gmail.pmanenok.data.repository

import com.gmail.pmanenok.data.entity.antibiotics.Amoxicillin
import com.gmail.pmanenok.data.entity.antibiotics.Azithromycinum
import com.gmail.pmanenok.data.entity.antibiotics.Cefdinir
import com.gmail.pmanenok.data.entity.antibiotics.Cefuroximum
import com.gmail.pmanenok.data.entity.antibiotics.Clarithromicin
import com.gmail.pmanenok.data.entity.antibiotics.Clavulanic
import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.AntibioticType
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.repository.DrugRepository
import io.reactivex.Flowable

class AntibioticRepositoryImpl : DrugRepository {
    private val antibiotics =
        arrayListOf(Amoxicillin, Clavulanic, Clarithromicin, Cefuroximum, Azithromycinum, Cefdinir)

    override fun getDrug(type: TypedEnum): Flowable<BaseDrug> {
        return Flowable.fromArray(antibiotics[type.getId()])
    }

    override fun getDrugTypesList(): Flowable<Array<TypedEnum>> {
        return Flowable.fromArray(AntibioticType.values()).map {
            val test = mutableListOf<TypedEnum>()
            test.addAll(it)
            return@map test.toTypedArray()
        }
    }
}