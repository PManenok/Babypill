package com.gmail.pmanenok.antibiocalc.presentation.screens.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.databinding.ActivityMainBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseMvvmActivity
import com.google.android.gms.ads.AdRequest

class MainActivity : BaseMvvmActivity<MainViewModel, MainRouter, ActivityMainBinding>() {
    override fun prodiveViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun provideRouter(): MainRouter {
        return MainRouter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainAdView.loadAd(AdRequest.Builder().build())
        router.goToMainMenu()
    }
}