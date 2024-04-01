package com.gmail.pmanenok.antibiocalc.presentation.app

import android.app.Activity
import android.app.Application
import com.gmail.pmanenok.antibiocalc.presentation.inject.AppComponent
import com.gmail.pmanenok.antibiocalc.presentation.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    companion object {
        lateinit var instance: App

        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this).build()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}