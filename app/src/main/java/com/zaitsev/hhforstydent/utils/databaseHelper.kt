package com.zaitsev.hhforstydent.utils

import com.google.firebase.auth.FirebaseAuth

lateinit var AUTH: FirebaseAuth
lateinit var UID: String

const val NODE_USERS = "User"
const val NODE_PLACES = "Place"
const val NODE_PORTFOLIO = "Portfolio"
const val NODE_PLACES_LIKE = "FavoritePlace"

const val CHILD_EMAIL = "email"

const val CHILD_NAME = "Name"
const val CHILD_SURNAME = "Surname"
const val CHILD_MIDDLE_NAME = "Middle_name"
const val CHILD_SPECIAL = "Spec"
const val CHILD_STACK = "Stack"
const val CHILD_COURSE = "Well"
const val CHILD_LANGUAGE = "Language"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    UID = AUTH.currentUser?.uid.toString()
}

