package com.dara.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dara.users.data.User
import com.dara.users.databinding.FragmentUsersBinding

/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment(R.layout.fragment_users) {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private var users = listOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUsers()

    }

    private fun getUsers() {
        viewModel.users.observe(viewLifecycleOwner, {
            if (it != null) {
                users = it
                for (user in users) {
                    binding.tvNames.append(user.lastName + "\n")
                }

            }
        })
    }
}