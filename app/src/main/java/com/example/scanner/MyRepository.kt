package com.example.scanner

import java.util.*
import javax.inject.Inject

class MyRepository @Inject constructor() {
    suspend fun generateChartData(): List<Float> {
        // Генерация случайных данных
        return List(10) { Random().nextFloat() }
    }
}