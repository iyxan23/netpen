package com.iyxan23.netpen.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.iyxan23.netpen.models.ChatItem
import com.iyxan23.netpen.repositories.FirestoreRepository
import kotlinx.coroutines.launch

class ChatsListViewModel : ViewModel() {
    private val chatsMutable = MutableLiveData<ArrayList<ChatItem>>()
    val chats: LiveData<ArrayList<ChatItem>> = chatsMutable

    init {
        viewModelScope.launch {
            chatsMutable.value = ArrayList()

            val auth = FirebaseAuth.getInstance()
            val uid = auth.uid

            val ourProfile = FirestoreRepository.fetchProfile(uid!!)

            // TODO: 5/8/21 This code is very unoptimized, we might want to add caching
            ourProfile.joinedGroups.forEach { groupId ->
                val groupInfo = FirestoreRepository.fetchGroupInfo(groupId)

                // Is this "group" a direct message?
                if (groupInfo.isDm) {
                    // Then fetch the other side's uid and username
                    var otherSideUid: String? = null

                    // groupInfo.publicKeys contains the members of that group and it's
                    // corresponding public key
                    groupInfo.publicKeys.forEach { entry ->
                        // entry.key is the uid, entry.value is the public key
                        if (entry.key != uid) {
                            otherSideUid = entry.key
                        }
                    }

                    if (otherSideUid == null) {
                        return@forEach
                    }

                    val otherSideProfile = FirestoreRepository.fetchProfile(otherSideUid!!)

                    // Add that to the ChatsList
                    chatsMutable.value!!.add(ChatItem(otherSideUid!!, otherSideProfile.username))
                } else {
                    TODO("Implement group thing")
                }
            }
        }
    }
}
