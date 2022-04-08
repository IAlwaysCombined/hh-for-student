package com.zaitsev.hhforstydent.utils

import com.google.firebase.auth.FirebaseAuth

lateinit var AUTH: FirebaseAuth
lateinit var UID: String

const val NODE_USERS = "User"
const val NODE_PLACES = "Place"
const val NODE_PORTFOLIO = "Portfolio"
const val NODE_VACANCY = "Vacancy"
const val NODE_PLACES_LIKE = "FavoritePlace"
const val NODE_ORDER = "Order"

const val CHILD_EMAIL = "email"
const val CHILD_COMPANY_NAME = "CompanyName"
const val CHILD_END_DATE = "EndDate"
const val CHILD_IMAGE = "Image"

const val CHILD_NAME = "Name"
const val CHILD_ORDER_USER = "OrderUser"
const val CHILD_SURNAME = "Surname"
const val CHILD_VACANCY = "Vacancy"
const val CHILD_MIDDLE_NAME = "Middle_name"
const val CHILD_SPECIAL = "Spec"
const val CHILD_STACK = "Stack"
const val CHILD_START_DATE = "StartDate"
const val CHILD_COURSE = "Well"
const val CHILD_LANGUAGE = "Language"
const val CHILD_UID = "ID"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    UID = AUTH.currentUser?.uid.toString()
}

