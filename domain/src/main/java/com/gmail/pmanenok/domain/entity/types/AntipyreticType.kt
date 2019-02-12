package com.gmail.pmanenok.domain.entity.types

enum class AntipyreticType : TypedEnum {
    PARACETAMOLUM {
        override fun getId(): Int {
            return 0
        }
    },
    IBUPROPHENUM {
        override fun getId(): Int {
            return 1
        }
    };
    override fun getTypeId(): Int {
        return 1
    }
}

