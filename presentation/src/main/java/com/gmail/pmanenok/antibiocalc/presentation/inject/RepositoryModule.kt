package com.gmail.pmanenok.antibiocalc.presentation.inject

import com.gmail.pmanenok.antibiocalc.presentation.factory.RepositoryFactory
import com.gmail.pmanenok.data.repository.AntibioticRepositoryImpl
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
        ANTIBIOTIC, ANTIPYRETIC
    }

    @Provides
    fun provideRepositoryFactory():BaseRepoFactory<MenuType, DrugRepository>{
        return RepositoryFactory(provideAntibioticRepository(),provideAntipyreticRepository())
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

}

/*
*
* // Definition

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ShapeType {
  ShapeTypeEnum value(); /* default ShapeTypeEnum.RECTANGLE; */
}

public enum ShapeTypeEnum {
  RECTANGLE, CIRCLE
}

// Usage

@Provides @ShapeType(ShapeTypeEnum.RECTANGLE)
public Shape provideRectangle() {
    return new Rectangle();
}

@Inject @ShapeType(ShapeTypeEnum.RECTANGLE) Shape rectangle;
*
* */

/*
error: [Dagger/DuplicateBindings] com.gmail.pmanenok.domain.repository.DrugRepository is bound multiple times:
public abstract void inject(@org.jetbrains.annotations.NotNull()
^
@org.jetbrains.annotations.NotNull @Binds com.gmail.pmanenok.domain.repository.DrugRepository com.gmail.pmanenok.antibiocalc.presentation.inject.RepositoryModule.provideAntibioticRepository(com.gmail.pmanenok.data.repository.AntibioticRepositoryImpl)
@org.jetbrains.annotations.NotNull @Binds com.gmail.pmanenok.domain.repository.DrugRepository com.gmail.pmanenok.antibiocalc.presentation.inject.RepositoryModule.provideAntipyreticRepository(com.gmail.pmanenok.data.repository.AntipyreticRepositoryImpl)

com.gmail.pmanenok.domain.repository.DrugRepository is injected at
com.gmail.pmanenok.domain.usecase.antibiotic.GetDrugUseCase.<init>(…, arg1)
com.gmail.pmanenok.domain.usecase.antibiotic.GetDrugUseCase is injected at
com.gmail.pmanenok.antibiocalc.presentation.screens.main.ResultViewModel.getDrugUseCase
com.gmail.pmanenok.antibiocalc.presentation.screens.main.ResultViewModel is injected at
com.gmail.pmanenok.antibiocalc.presentation.inject.AppComponent.inject(com.gmail.pmanenok.antibiocalc.presentation.screens.main.ResultViewModel)
        */