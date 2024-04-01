package com.gmail.pmanenok.antibiocalc.presentation.factory

import com.gmail.pmanenok.antibiocalc.presentation.inject.RepositoryModule
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.factory.BaseRepoFactory
import com.gmail.pmanenok.domain.repository.DrugRepository
import javax.inject.Inject

class RepositoryFactory @Inject constructor(
    @RepositoryModule.RepositoryType(RepositoryModule.RepositoryTypeEnum.ANTIBIOTIC)
    val antibioticRepository: DrugRepository,
    @RepositoryModule.RepositoryType(RepositoryModule.RepositoryTypeEnum.ANTIPYRETIC)
    val antipyreticRepository: DrugRepository,
    @RepositoryModule.RepositoryType(RepositoryModule.RepositoryTypeEnum.ANTICOUGH)
    val anticoughRepository: DrugRepository
) : BaseRepoFactory<MenuType, DrugRepository> {

    override fun create(type: MenuType): DrugRepository {
        return when (type) {
            MenuType.ANTIBIOTIC -> antibioticRepository
            MenuType.ANTIPYRETIC -> antipyreticRepository
            MenuType.ANTICOUGH -> anticoughRepository
        }
    }

}