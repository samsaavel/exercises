package com.greenv.feb14.network

sealed class NetworkResponse<out T : Any> {
    data class Failure(val error: Throwable) : NetworkResponse<Nothing>()
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
}