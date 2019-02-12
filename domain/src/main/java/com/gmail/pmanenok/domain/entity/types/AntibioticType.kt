package com.gmail.pmanenok.domain.entity.types

enum class AntibioticType : TypedEnum {
    AMOXICILLIN {
        override fun getId(): Int {
            return 0
        }
    },
    CLAVULANIC {
        override fun getId(): Int {
            return 1
        }
    },
    CLARITHROMICIN {
        override fun getId(): Int {
            return 2
        }
    },
    CEFUROXIMUM {
        override fun getId(): Int {
            return 3
        }
    },
    AZITHROMYCINUM {
        override fun getId(): Int {
            return 4
        }
    },
    CEFDINIR {
        override fun getId(): Int {
            return 5
        }
    };

    override fun getTypeId(): Int {
        return 0
    }
}

