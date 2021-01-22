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
    val usersInDatabase = userDao.getUsers()

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

    // Get user's details from database
    fun userDetailsInDatabase(userId: String) = userDao.getUserDetails(userId)

    // Get user details from server
    fun usersDetailsFromServer(userId: String) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.loading(null))
            val response = service.getUserDetails(userId)
            userDao.addUserDetails(response)
            emit(Resource.success(response))
        } catch (e: java.lang.Exception) {
            emit(Resource.error(e.localizedMessage, null))
        }
    }

}