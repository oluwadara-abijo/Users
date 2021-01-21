package com.dara.users.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dara.users.R
import com.dara.users.adapter.UsersAdapter
import com.dara.users.data.User
import com.dara.users.databinding.FragmentUsersBinding
import com.dara.users.utils.NetworkUtils
import com.dara.users.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment(R.layout.fragment_users), UsersAdapter.ItemClickListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var networkUtils: NetworkUtils
    private var users = listOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkUtils = NetworkUtils(requireActivity(), binding.progressBar.root)
        getUsers()
    }

    private fun getUsers() {
        if (networkUtils.isNetworkAvailable()) {
            networkUtils.showLoading()
            viewModel.users.observe(viewLifecycleOwner, {
                if (it != null) {
                    users = it
                    networkUtils.hideLoading()
                }
                setupRecyclerView()
            })
        } else {
            Toast.makeText(
                requireContext(), "Please check your internet connection", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupRecyclerView() {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = UsersAdapter(users, requireContext(), this@UsersFragment)
            networkUtils.hideLoading()
        }
    }

    override fun onItemClick(user: User) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(
            R.id.fragment_container,
            UsersDetailFragment.newInstance(user.id)
        )?.addToBackStack(null)?.commit()
    }
}