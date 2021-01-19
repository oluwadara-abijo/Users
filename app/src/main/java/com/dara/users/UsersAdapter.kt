package com.dara.users

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dara.users.data.User
import com.dara.users.databinding.ListItemUserBinding

class UsersAdapter(private val users: List<User>, private val context: Context) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private lateinit var binding: ListItemUserBinding

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = binding.tvName
        private val imageView = binding.imgProfilePicture
        fun bind(user: User) {
            textView.text = fullName(user)
            Glide.with(context).load(user.picture).transform(CircleCrop()).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ListItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    /**
     *  Concatenates user's first and last name
     *  @param user User whose full name is to be returned
     *  @return User's full name
     */
    private fun fullName(user: User): String {
        return "${user.firstName} ${user.lastName}"
    }
}