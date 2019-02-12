package com.gmail.pmanenok.domain.usecase.drug

import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.factory.BaseRepoFactory
import com.gmail.pmanenok.domain.repository.DrugRepository
import com.gmail.pmanenok.domain.usecase.BaseUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetDrugListUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread
) : BaseUseCase(postExecutorThread) {

    var type: MenuType = MenuType.ANTIBIOTIC

    @Inject
    lateinit var factory: BaseRepoFactory<MenuType, DrugRepository>

    fun getDrugTypesList(): Flowable<Array<TypedEnum>> {
        return factory.create(type).getDrugTypesList()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
