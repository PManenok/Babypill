package com.gmail.pmanenok.antibiocalc.presentation.utils

fun roundFloat(value: Float): String {
    return when {
        (value % 1) == 0f -> value.toInt().toString()
        (value * 10 % 1) == 0f -> (((value * 10f).toInt()) / 10f).toString()
        else -> value.toString()
    }
}