package com.dara.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dara.users.data.ApiResponse
import com.dara.users.data.User

class MainViewModel : ViewModel() {

    private val repository = Repository()

    val users: LiveData<List<User>?> = repository.getUsers()
}