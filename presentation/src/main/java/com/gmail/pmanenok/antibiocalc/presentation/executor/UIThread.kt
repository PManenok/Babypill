package com.gmail.pmanenok.antibiocalc.presentation.executor

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutorThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}