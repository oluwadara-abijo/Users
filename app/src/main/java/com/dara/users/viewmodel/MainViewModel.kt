package com.dara.users.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dara.users.Repository
import com.dara.users.data.Resource
import com.dara.users.data.User
import com.dara.users.data.UserDetails
import com.dara.users.database.UsersDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    val users: LiveData<List<User>>?
    val usersFromServer: LiveData<Resource<List<User>>>

    init {
        val userDao = UsersDatabase.getDatabase(application).usersDao()
        repository = Repository(userDao)
        users = repository.usersInDatabase
        usersFromServer = repository.usersFromServer
    }

    fun getUserDetailsFromDatabase(id: String) = repository.userDetailsInDatabase(id)

    fun getUserDetailsFromServer(id: String) = repository.usersDetailsFromServer(id)

}