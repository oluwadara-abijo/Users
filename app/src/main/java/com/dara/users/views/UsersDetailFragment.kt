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
import com.dara.users.data.Status
import com.dara.users.data.UserDetails
import com.dara.users.databinding.FragmentUsersDetailsBinding
import com.dara.users.utils.NetworkUtils
import com.dara.users.viewmodel.MainViewModel
import java.text.SimpleDateFormat
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

    /**
     * Get user's details from database or server if database is empty
     */
    private fun getUserDetails(userId: String) {
        //Get users from database
        viewModel.getUserDetailsFromDatabase(userId)?.observe(viewLifecycleOwner, {
            if (it != null) {
                userDetails = it
                populateUI(userDetails)
            }
            // The database has no user details records; get data from the server
            if (it == null) {
                // Check for internet connectivity
                if (networkUtils.isNetworkAvailable()) {
                    viewModel.getUserDetailsFromServer(userId).observe(viewLifecycleOwner, { res ->
                        when (res.status) {
                            Status.LOADING -> networkUtils.showLoading()
                            Status.SUCCESS -> {
                                networkUtils.hideLoading()
                                userDetails = res.data!!
                                populateUI(userDetails)
                            }
                            Status.ERROR -> {
                                networkUtils.hideLoading()
                                Toast.makeText(requireContext(), res.message, Toast.LENGTH_LONG)
                                    .show()
                            }

                        }
                    })
                } else {
                    Toast.makeText(
                        requireContext(), getString(R.string.internet_error), Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

    }

    private fun populateUI(userDetails: UserDetails?) {
        if (userDetails != null) {
            binding.groupLabels.visibility = View.VISIBLE
            binding.tvTitle.text = userDetails.title.capitalize(Locale.getDefault())
            binding.tvFirstName.text = userDetails.firstName
            binding.tvLastName.text = userDetails.lastName
            binding.tvEmail.text = userDetails.email
            binding.tvPhone.text = userDetails.phone
            binding.tvBirthday.text = formatDate(userDetails.dateOfBirth)
            binding.tvLocation.text = cityAndCountry(userDetails)
            Glide.with(requireContext()).load(userDetails.picture).transform(CircleCrop())
                .into(binding.imgProfilePicture)
        }

    }

    // Formats user's date of birth in readable form
    private fun formatDate(timeString: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return formatter.format(parser.parse(timeString))
    }

    private fun cityAndCountry(userDetails: UserDetails): String {
        return "${userDetails.location.city}, ${userDetails.location.country}"
    }
}