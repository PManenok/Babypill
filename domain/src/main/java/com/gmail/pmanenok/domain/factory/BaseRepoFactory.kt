package com.gmail.pmanenok.domain.factory

import com.gmail.pmanenok.domain.entity.types.TypedEnum
import com.gmail.pmanenok.domain.repository.BaseRepository

interface BaseRepoFactory<T: TypedEnum, R: BaseRepository>{
    fun create(type: T):R
}