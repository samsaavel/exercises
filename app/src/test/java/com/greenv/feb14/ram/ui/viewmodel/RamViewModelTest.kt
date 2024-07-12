package com.greenv.feb14.ram.ui.viewmodel

import com.greenv.feb14.network.NetworkResponse
import com.greenv.feb14.repository.RamRepositoryContract
import com.greenv.feb14.response.RamResponse
import com.greenv.feb14.ui.RamState
import com.greenv.feb14.ui.viewmodel.RamViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RamViewModelTest {

    private val unconfined = Dispatchers.Unconfined

    @MockK
    private lateinit var ramRepo: RamRepositoryContract

    @MockK
    private lateinit var fakeResponse: RamResponse

    private val failure = NetworkResponse.Failure(Error("TEST ERROR"))
    private val success = NetworkResponse.Success(fakeResponse)
    private lateinit var ramViewModel: RamViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(unconfined)
        ramViewModel = RamViewModel(ramRepo)
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
        assertTrue(ramViewModel.ramState.value is RamState.Failure)
    }

    @Test
    fun `fetchCharacters publishes success with data when repo send success`() {
        //Given
        coEvery { ramRepo.fetchCharacters() }.returns(success)
        coEvery { success.data }.returns(fakeResponse)

        //When
        ramViewModel.fetchCharacters()

        //Then
        assertTrue(ramViewModel.ramState.value is RamState.Success)
    }
}