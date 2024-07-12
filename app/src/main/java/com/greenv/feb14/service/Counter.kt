package com.greenv.feb14.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Counter {
    private var counterValue: Int = 0
    private var isRunning: Boolean = true

    fun start(): Flow<Int> = flow {
        while (isRunning) {
            emit(counterValue)
            delay(1000)
            counterValue++
        }
    }

    fun stop() {
        isRunning = false
        counterValue = 0
    }
}