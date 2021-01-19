package com.dara.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val repository = Repository()

    val users: LiveData<Any?> = repository.getUsers()
}