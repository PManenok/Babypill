package com.gmail.pmanenok.data.entity.antipyretics

import org.junit.Assert
import org.junit.Test

class AntipyreticTest {
    private val antipyretics = arrayListOf(Paracetamolum, Ibuprophenum)

    @Test
    fun testResults() {
        for (antipyretic in antipyretics) {
            var doseInd = 0
            while (doseInd < antipyretic.dosesList.size) {
                var weight = 1
                while (weight <= 40) {
                    Assert.assertTrue(
                        "Antipyretic: ${antipyretic.type.name}, weight = $weight, dosageIndex = $doseInd",
                        antipyretic.getResults(weight, doseInd).isNotEmpty()
                    )
                    weight++
                }
                doseInd++
            }
        }
    }
}