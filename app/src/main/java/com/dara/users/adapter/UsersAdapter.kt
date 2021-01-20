package com.dara.users.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dara.users.data.User
import com.dara.users.data.fullName
import com.dara.users.databinding.ListItemUserBinding

class UsersAdapter(
    private val users: List<User>,
    private val context: Context,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private lateinit var binding: ListItemUserBinding

    interface ItemClickListener {
        fun onItemClick(user: User)
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = binding.tvName
        private val imageView = binding.imgProfilePicture
        fun bind(user: User) {
            textView.text = fullName(user)
            Glide.with(context).load(user.picture).transform(CircleCrop()).into(imageView)
            itemView.setOnClickListener {listener.onItemClick(user) }
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

}