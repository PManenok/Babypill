package com.gmail.pmanenok.domain.entity.types

import java.io.Serializable

interface TypedEnum : Serializable {
    fun getId(): Int
    fun getTypeId(): Int
}