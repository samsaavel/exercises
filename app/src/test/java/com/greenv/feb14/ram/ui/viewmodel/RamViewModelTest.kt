package com.greenv.feb14.ram.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.greenv.feb14.network.NetworkResponse
import com.greenv.feb14.repository.RamRepositoryContract
import com.greenv.feb14.response.RamResponse
import com.greenv.feb14.ui.RamState
import com.greenv.feb14.ui.RamViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RamViewModelTest {

    @MockK
    lateinit var ramRepo: RamRepositoryContract

    @MockK
    private lateinit var ramViewModel: RamViewModel

    @MockK
    private lateinit var failure: NetworkResponse.Failure

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        ramViewModel = RamViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCharacters publishes failure on repo not sending data`() {
        //Given
        coEvery { ramRepo.fetchCharacters() }.returns(failure)

        //When
        ramViewModel.fetchCharacters()

        //Then
        assert(ramViewModel.ramState.value is RamState.Failure)
    }
}