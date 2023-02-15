package com.greenv.feb14.ui

import com.greenv.feb14.response.RamResponse

sealed class RamState {

    object None : RamState()
    object Loading : RamState()
    object Failure : RamState()
    data class Success(val characters: RamResponse) : RamState()
}