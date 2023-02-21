package com.greenv.feb14.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenv.feb14.network.NetworkLibrary.ramApi
import com.greenv.feb14.network.NetworkResponse
import com.greenv.feb14.repository.RamRepository
import com.greenv.feb14.repository.RamRepositoryContract
import com.greenv.feb14.response.RamResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RamViewModel(
    private val ramRepo: RamRepositoryContract = RamRepository(ramApi = ramApi),
) : ViewModel() {
    private val _ramState: MutableStateFlow<RamState> = MutableStateFlow(RamState.None)
    val ramState: StateFlow<RamState> by lazy { _ramState }

    @VisibleForTesting
    fun fetchCharacters() {
        _ramState.value = RamState.Loading

        viewModelScope.launch {
            val response = fetchCharactersFromRepo()
            _ramState.value = when (response) {
                is NetworkResponse.Failure -> RamState.Failure
                is NetworkResponse.Success -> RamState.Success(response.data)
            }
        }
    }

    @VisibleForTesting
    suspend fun fetchCharactersFromRepo(): NetworkResponse<RamResponse> {
        return viewModelScope.async {
            return@async ramRepo.fetchCharacters()
        }.await()
    }
}