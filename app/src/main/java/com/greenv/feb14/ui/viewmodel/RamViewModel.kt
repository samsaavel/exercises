package com.greenv.feb14.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenv.feb14.network.NetworkLibrary
import com.greenv.feb14.network.NetworkResponse
import com.greenv.feb14.repository.RamRepository
import com.greenv.feb14.repository.RamRepositoryContract
import com.greenv.feb14.ui.RamIntent
import com.greenv.feb14.ui.RamState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RamViewModel() : ViewModel() {
    private val ramRepo: RamRepositoryContract = RamRepository(ramApi = NetworkLibrary().ramApi)
    private val _ramState: MutableStateFlow<RamState> = MutableStateFlow(RamState.None)
    val ramState: StateFlow<RamState> by lazy { _ramState }

    @VisibleForTesting
    fun fetchCharacters() {
        _ramState.value = RamState.Loading
        viewModelScope.launch {
            _ramState.value =
                when (val response = ramRepo.fetchCharacters()) {
                    NetworkResponse.Failure -> RamState.Failure
                    is NetworkResponse.Success -> RamState.Success(response.data)
                }
        }
    }

    fun handleFetchCharacters(intent: RamIntent) {
        when (intent) {
            RamIntent.getCharactersIntent -> fetchCharacters()
        }
    }
}