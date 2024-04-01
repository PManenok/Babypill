package com.gmail.pmanenok.antibiocalc.presentation.inject

import com.gmail.pmanenok.antibiocalc.presentation.factory.RepositoryFactory
import com.gmail.pmanenok.data.repository.AntibioticRepositoryImpl
import com.gmail.pmanenok.data.repository.AnticoughRepositoryImpl
import com.gmail.pmanenok.data.repository.AntipyreticRepositoryImpl
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.factory.BaseRepoFactory
import com.gmail.pmanenok.domain.repository.DrugRepository
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class RepositoryModule {
    @Qualifier
    @MustBeDocumented
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    annotation class RepositoryType(val value: RepositoryTypeEnum)

    enum class RepositoryTypeEnum {
        ANTIBIOTIC, ANTIPYRETIC, ANTICOUGH
    }

    @Provides
    fun provideRepositoryFactory(): BaseRepoFactory<MenuType, DrugRepository> {
        return RepositoryFactory(provideAntibioticRepository(), provideAntipyreticRepository(), provideAnticoughRepository())
    }

    @RepositoryType(RepositoryTypeEnum.ANTIBIOTIC)
    fun provideAntibioticRepository(): DrugRepository {
        return AntibioticRepositoryImpl()
    }

    @Provides
    @RepositoryType(RepositoryTypeEnum.ANTIPYRETIC)
    fun provideAntipyreticRepository(): DrugRepository {
        return AntipyreticRepositoryImpl()
    }

    @RepositoryType(RepositoryTypeEnum.ANTICOUGH)
    fun provideAnticoughRepository(): DrugRepository {
        return AnticoughRepositoryImpl()
    }

}