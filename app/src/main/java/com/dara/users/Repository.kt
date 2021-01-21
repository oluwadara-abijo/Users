package com.dara.users

import androidx.lifecycle.liveData
import com.dara.users.data.User
import com.dara.users.database.UserDao
import com.dara.users.network.DummyApiClient
import kotlinx.coroutines.Dispatchers

/**
 * This class handles data operations
 */

class Repository(private val userDao: UserDao) {
    private val service = DummyApiClient.getService()

    fun getUsers() = liveData(Dispatchers.IO) {
        if (getUsersOffline().value?.isNotEmpty() == true) {
            emit(getUsersOffline().value)
        } else {
            try {
                val registerResponse = service.getUsers().data
                emit(registerResponse)
            } catch (e: java.lang.Exception) {
                emit(null)
            }
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

    fun insertUser(user: User) = liveData(Dispatchers.IO) {
        emit(userDao.insertUser(user))
    }

    // Gets users from database
    private fun getUsersOffline() = liveData(Dispatchers.IO) {
        emit(userDao.getUsers())
    }
}