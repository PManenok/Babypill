package com.gmail.pmanenok.domain.usecase.drug

import com.gmail.pmanenok.domain.entity.drug.BaseDrug
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.factory.BaseRepoFactory
import com.gmail.pmanenok.domain.repository.DrugRepository
import com.gmail.pmanenok.domain.usecase.BaseUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetDrugUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread
) : BaseUseCase(postExecutorThread) {

    var type: MenuType = MenuType.ANTIBIOTIC

    @Inject
    lateinit var factory: BaseRepoFactory<MenuType, DrugRepository>

    fun getDrug(type: TypedEnum): Flowable<BaseDrug> {
        return factory.create(this.type).getDrug(type)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
