package com.iyxan23.netpen.models

import com.google.firebase.firestore.PropertyName

data class GroupInfo(
    @PropertyName("is_dm")
    val isDm: Boolean,

    @PropertyName("public_keys")
    // uid: String, public_key: String
    val publicKeys: HashMap<String, String>,

    @PropertyName("rtdb_id")
    val rtdbID: String
)
