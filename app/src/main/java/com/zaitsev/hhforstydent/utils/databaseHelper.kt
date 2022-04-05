package com.zaitsev.hhforstydent.utils

import com.google.firebase.auth.FirebaseAuth

lateinit var AUTH: FirebaseAuth
lateinit var UID: String

const val NODE_USERS = "User"
const val NODE_PLACES = "Place"
const val NODE_PLACES_LIKE = "FavoritePlace"
const val CHILD_EMAIL = "email"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    UID = AUTH.currentUser?.uid.toString()
}

