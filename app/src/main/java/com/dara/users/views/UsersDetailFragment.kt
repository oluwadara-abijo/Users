package com.dara.users.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dara.users.R
import com.dara.users.data.User
import com.dara.users.data.fullName
import com.dara.users.databinding.FragmentUsersDetailsBinding
import java.util.*

class UsersDetailFragment : Fragment(R.layout.fragment_users_details) {
    private var _binding: FragmentUsersDetailsBinding? = null
    private val binding get() = _binding!!
    private var user: User? = null

    companion object {
        private const val USER = "user"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param user Parameter 1.
         * @return A new instance of fragment UsersFragment.
         */
        @JvmStatic
        fun newInstance(user: User) =
            UsersDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER, user)
                }
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        user = arguments?.getParcelable(USER)
        _binding = FragmentUsersDetailsBinding.inflate(inflater, container, false)
        populateUI(user)
        return binding.root
    }

    private fun populateUI(user: User?) {
        if (user != null) {
            binding.tvTitle.text = user.title.capitalize(Locale.getDefault())
            binding.tvName.text = fullName(user)
            binding.tvEmail.text = user.email
            Glide.with(requireContext()).load(user.picture).transform(CircleCrop())
                .into(binding.imgProfilePicture)
        }

    }
}