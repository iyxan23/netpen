package com.iyxan23.netpen.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class UserProfile(
    @DocumentId
    val uid: String,

    val username: String,
    @PropertyName("joined_groups")
    val joinedGroups: List<String>,
)