package com.gmail.pmanenok.domain.usecase

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase() {
    lateinit var postExecutorThread: Scheduler
    var workExecutorThread: Scheduler = Schedulers.io()

    constructor(
        postExecutorThread: Scheduler,
        workExecutorThread: Scheduler = Schedulers.io()
    ) : this() {
        this.postExecutorThread = postExecutorThread
        this.workExecutorThread = workExecutorThread
    }

    constructor(postExecutorThread: PostExecutorThread) : this(postExecutorThread.getScheduler())
}