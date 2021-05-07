package com.iyxan23.netpen.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iyxan23.netpen.R

class ChatListRecyclerViewAdapter : RecyclerView.Adapter<ChatListRecyclerViewAdapter.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val body: View = itemView.findViewById(R.id.body)
        val username: TextView = itemView.findViewById(R.id.username)
    }
}