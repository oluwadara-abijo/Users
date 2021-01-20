package com.dara.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dara.users.Repository
import com.dara.users.data.User
import com.dara.users.data.UserDetails

class MainViewModel : ViewModel() {

    private val repository = Repository()

    val users: LiveData<List<User>?> = repository.getUsers()

    fun getUserDetails(userId: String): LiveData<UserDetails?> = repository.getUserDetails(userId)

}