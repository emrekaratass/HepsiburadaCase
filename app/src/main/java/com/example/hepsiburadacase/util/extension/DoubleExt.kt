package com.example.hepsiburadacase.util.extension

fun Double?.ignoreNull(defaultValue: Double = 0.0): Double = this ?: defaultValue
