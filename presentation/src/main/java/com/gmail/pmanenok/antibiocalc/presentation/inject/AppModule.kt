package com.gmail.pmanenok.antibiocalc.presentation.inject

import android.content.Context
import com.gmail.pmanenok.antibiocalc.presentation.app.App
import com.gmail.pmanenok.antibiocalc.presentation.executor.UIThread
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()
}