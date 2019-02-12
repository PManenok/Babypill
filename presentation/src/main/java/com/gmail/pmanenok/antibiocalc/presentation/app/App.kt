package com.gmail.pmanenok.antibiocalc.presentation.app

import android.app.Activity
import android.app.Application
import com.gmail.pmanenok.antibiocalc.presentation.inject.AppComponent
import com.gmail.pmanenok.antibiocalc.presentation.inject.DaggerAppComponent
import com.google.android.gms.ads.MobileAds
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    companion object {
        lateinit var instance: App
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        MobileAds.initialize(this, "ca-app-pub-6232913731101651~6806286977")
    }
}