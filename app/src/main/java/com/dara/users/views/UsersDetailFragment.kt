package com.dara.users.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dara.users.R
import com.dara.users.data.UserDetails
import com.dara.users.databinding.FragmentUsersDetailsBinding
import com.dara.users.utils.NetworkUtils
import com.dara.users.viewmodel.MainViewModel
import java.util.*

class UsersDetailFragment : Fragment(R.layout.fragment_users_details) {
    private var _binding: FragmentUsersDetailsBinding? = null
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var networkUtils: NetworkUtils
    private val binding get() = _binding!!
    private var userId: String? = null
    private lateinit var userDetails: UserDetails

    companion object {
        private const val USER_ID = "id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param userId Parameter 1.
         * @return A new instance of fragment UsersFragment.
         */
        @JvmStatic
        fun newInstance(userId: String) =
            UsersDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_ID, userId)
                }
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        userId = arguments?.getString(USER_ID)
        _binding = FragmentUsersDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkUtils = NetworkUtils(requireActivity(), binding.progressBar.root)
        userId?.let { getUserDetails(it) }
    }

    private fun getUserDetails(userId: String) {
        if (networkUtils.isNetworkAvailable()) {
            networkUtils.showLoading()
            viewModel.getUserDetails(userId).observe(viewLifecycleOwner, { res ->
                if (res != null) {
                    userDetails = res
                    populateUI(userDetails)
                }
                networkUtils.hideLoading()
            })
        } else {
            Toast.makeText(
                requireContext(), "Please check your internet connection", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun populateUI(userDetails: UserDetails?) {
        if (userDetails != null) {
            binding.tvTitle.text = userDetails.title.capitalize(Locale.getDefault())
            binding.tvFirstName.text = userDetails.firstName
            binding.tvLastName.text = userDetails.lastName
            binding.tvEmail.text = userDetails.email
            binding.tvPhone.text = userDetails.phone
            binding.tvBirthday.text = userDetails.dateOfBirth
            binding.tvLocation.text = userDetails.location.country
            Glide.with(requireContext()).load(userDetails.picture).transform(CircleCrop())
                .into(binding.imgProfilePicture)
        }

    }
}