package com.dara.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment(R.layout.fragment_users) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUsers()

    }

    fun getUsers() {
        viewModel.users.observe(viewLifecycleOwner, {
            println("Result - $it")
        })
    }
}