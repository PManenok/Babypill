package com.gmail.pmanenok.domain.entity.types

enum class AnticoughType : TypedEnum {
    AMBROXOL {
        override fun getId(): Int {
            return 0
        }
    },
    ACETYLCYSTEINE {
        override fun getId(): Int {
            return 1
        }
    }/*,
    THEOPHYLLINUM_GUAIFENESINUM {
        override fun getId(): Int {
            return 2
        }
    }*/;
    override fun getTypeId(): Int {
        return 2
    }
}

