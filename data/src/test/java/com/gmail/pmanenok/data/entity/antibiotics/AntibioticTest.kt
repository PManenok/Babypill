package com.gmail.pmanenok.data.entity.antibiotics

import org.junit.Assert
import org.junit.Test

class AntibioticTest {
    private val antibiotics =
        arrayListOf(Amoxicillin, Clavulanic, Clarithromicin, Cefuroximum, Azithromycinum, Cefdinir)
    @Test
    fun testResults() {
        for (antibiotic in antibiotics){
            var doseInd = 0
            while (doseInd<antibiotic.dosesList.size){
                var weight = 1
                while (weight<=40){
                    Assert.assertTrue("Antibiotic: ${antibiotic.type.name}, weight = $weight, dosageIndex = $doseInd",
                        antibiotic.getResults(weight, doseInd).isNotEmpty()
                    )
                    weight++
                }
                doseInd++
            }
        }
    }
}