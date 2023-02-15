package com.greenv.feb14.repository

import android.util.Log
import com.greenv.feb14.network.NetworkResponse
import com.greenv.feb14.network.RamApi
import com.greenv.feb14.response.RamResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RamRepositoryContract {
    suspend fun fetchCharacters(): NetworkResponse<RamResponse>
}

class RamRepository(
    private val ramApi: RamApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RamRepositoryContract {

    override suspend fun fetchCharacters(): NetworkResponse<RamResponse> =
        withContext(dispatcher) {
            try {
                val responseFromApi = ramApi.getCharacters()
                NetworkResponse.Success(responseFromApi)
            } catch (e: Throwable) {
                Log.d("vaneDebug", "fetchCharacters: " +
                        e.localizedMessage!!.toString())
                NetworkResponse.Failure(e)
            }
        }
}