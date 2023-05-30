package com.srgrsj.tyb.data.firebase.auth

import com.srgrsj.tyb.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AccountData {
    var ACCOUNT_AVATAR = R.drawable.gofman
    var EMAIL = Firebase.auth.currentUser?.email
    var ID = Firebase.auth.currentUser?.uid
}