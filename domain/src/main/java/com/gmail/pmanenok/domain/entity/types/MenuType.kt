package com.gmail.pmanenok.domain.entity.types


enum class MenuType: TypedEnum {
    ANTIBIOTIC {
        override fun getId(): Int {
            return 0
        }
    },
    ANTIPYRETIC {
        override fun getId(): Int {
            return 1
        }
    },
    ANTICOUGH {
        override fun getId(): Int {
            return 2
        }
    };
    override fun getTypeId(): Int {
        return -1
    }
}