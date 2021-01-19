package com.dara.users

import androidx.lifecycle.liveData
import com.dara.users.network.DummyApiClient
import kotlinx.coroutines.Dispatchers

/**
 * This class handles data operations
 */

class Repository {
    private val service = DummyApiClient.getService()

    fun getUsers() = liveData(Dispatchers.IO) {
        try {
            val registerResponse = service.getUsers()
            emit(registerResponse)
        } catch (e: java.lang.Exception) {
            emit(null)
        }
    }
}