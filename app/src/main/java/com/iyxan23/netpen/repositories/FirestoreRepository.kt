package com.iyxan23.netpen.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.iyxan23.netpen.models.GroupInfo
import com.iyxan23.netpen.models.UserProfile
import kotlinx.coroutines.tasks.await

object FirestoreRepository {
    suspend fun fetchProfile(uid: String): UserProfile {
        val db = FirebaseFirestore.getInstance()

        val userdata = db   .collection("userdata")
                            .document(uid)
                            .get()
                            .await()

        return userdata.toObject(UserProfile::class.java)!!
    }

    suspend fun fetchGroupInfo(id: String): GroupInfo {
        val db = FirebaseFirestore.getInstance()

        val group = db  .collection("groups")
                        .document(id)
                        .get()
                        .await()

        return group.toObject(GroupInfo::class.java)!!
    }
}