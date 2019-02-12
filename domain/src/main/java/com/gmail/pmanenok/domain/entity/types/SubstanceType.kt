package com.gmail.pmanenok.domain.entity.types

enum class SubstanceType : TypedEnum {
    SUSPENSION {
        override fun getId(): Int {
            return 0
        }
    },
    SYRUP {
        override fun getId(): Int {
            return 1
        }
    },
    SUPPOSITORY {
        override fun getId(): Int {
            return 2
        }
    },
    PILL {
        override fun getId(): Int {
            return 3
        }
    };

    override fun getTypeId(): Int {
        return 3
    }
}