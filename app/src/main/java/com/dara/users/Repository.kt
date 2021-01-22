package com.dara.users

import androidx.lifecycle.liveData
import com.dara.users.data.Resource
import com.dara.users.database.UserDao
import com.dara.users.network.DummyApiClient
import kotlinx.coroutines.Dispatchers

/**
 * This class handles data operations
 */

class Repository(private val userDao: UserDao) {
    private val service = DummyApiClient.getService()

    // Get users from database
    val usersInDatabase = Resource.success(userDao.getUsers())

    // Get users from server
    val usersFromServer = liveData(Dispatchers.IO) {
        try {
            emit(Resource.loading(null))
            val response = service.getUsers().data
            for (user in response) {
                userDao.addUser(user)
            }
            emit(Resource.success(response))
        } catch (e: java.lang.Exception) {
            emit(Resource.error(e.localizedMessage, null))
        }
    }

    fun getUserDetails(userId: String) = liveData(Dispatchers.IO) {
        try {
            val registerResponse = service.getUserDetails(userId)
            emit(registerResponse)
        } catch (e: java.lang.Exception) {
            emit(null)
        }
    }

}