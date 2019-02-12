package com.gmail.pmanenok.antibiocalc.presentation.screens.main

import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseViewModel

class MainViewModel : BaseViewModel<MainRouter>() {
    init {
        App.appComponent.inject(this)
    }
}