package com.greenv.feb14.ram.repositories

import android.util.Log
import com.greenv.feb14.network.NetworkResponse
import com.greenv.feb14.network.RamApi
import com.greenv.feb14.repository.RamRepository
import com.greenv.feb14.response.RamResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RamRepositoryTest {

    private lateinit var ramRepo: RamRepository

    @MockK
    private lateinit var ramApi: RamApi

    @MockK
    private lateinit var fakeRamResponse: RamResponse

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        MockKAnnotations.init(this)
        ramRepo = RamRepository(ramApi)
    }


    @Test
    fun `fetchCharactersFromApi returns failure on network exception`() = runBlocking {
        //Given
        coEvery { ramApi.getCharacters() }.throws(IllegalStateException(""))

        //When
        val response = ramRepo.fetchCharacters()

        //Assert
        assert(response is NetworkResponse.Failure)
    }


    @Test
    fun `fetchCharactersFromApi returns Success on network response received`() =
        runBlocking {
            //Given
            coEvery { ramApi.getCharacters() }.returns(fakeRamResponse)

            //When
            val response = ramRepo.fetchCharacters()

            //Assert
            assert(response is NetworkResponse.Success)
            assert((response as NetworkResponse.Success).data == fakeRamResponse)
        }
}