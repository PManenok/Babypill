package com.gmail.pmanenok.antibiocalc.presentation.inject

import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.calc.CalcViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.menu.MenuViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.ResultViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class,
        RepositoryModule::class, PresentationModule::class, DataModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(view: ResultViewModel)

    fun inject(view: CalcViewModel)

    fun inject(view: MainViewModel)

    fun inject(view: MenuViewModel)
}