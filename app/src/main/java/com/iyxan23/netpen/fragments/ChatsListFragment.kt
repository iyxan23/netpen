package com.iyxan23.netpen.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.iyxan23.netpen.R
import com.iyxan23.netpen.adapters.ChatListRecyclerViewAdapter
import com.iyxan23.netpen.viewmodels.ChatsListViewModel

class ChatsListFragment : Fragment() {

    companion object {
        fun newInstance() = ChatsListFragment()
    }

    private lateinit var viewModel: ChatsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.chats_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatsListViewModel::class.java)

        val root = requireView()
        val chatsListRecyclerView = root.findViewById<RecyclerView>(R.id.chats_list);
        val adapter = ChatListRecyclerViewAdapter()

        chatsListRecyclerView.adapter = adapter

        viewModel.chats.observe(viewLifecycleOwner, adapter::updateView)
    }
}