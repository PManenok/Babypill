package com.gmail.pmanenok.antibiocalc.presentation.screens.main.menu

import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseViewModel
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.recycler.TypedEnumAdapter
import com.gmail.pmanenok.domain.entity.types.MenuType


class MenuViewModel : BaseViewModel<MainRouter>() {
    init {
        App.appComponent.inject(this)
    }

    val adapter = TypedEnumAdapter(onItemClick = {
        router?.goToCalcFragment(it as MenuType)
    })

    init {
        adapter.addItems(MenuType.values().asList())
    }
}