package com.iyxan23.netpen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.iyxan23.netpen.R
import com.iyxan23.netpen.fragments.ChatsListFragmentDirections
import com.iyxan23.netpen.models.ChatItem

class ChatListRecyclerViewAdapter(private val chatItems: ArrayList<ChatItem>)
    : RecyclerView.Adapter<ChatListRecyclerViewAdapter.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.chat_list_item, null)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatItem: ChatItem = chatItems[position]

        holder.username.text = chatItem.other_side_username
        holder.body.setOnClickListener {
            val action =
                ChatsListFragmentDirections.actionChatsListFragmentToChatFragment(
                    chatItem.other_side_uid,
                    chatItem.other_side_username
                )

            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = chatItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val body: View = itemView.findViewById(R.id.body)
        val username: TextView = itemView.findViewById(R.id.username)
    }
}