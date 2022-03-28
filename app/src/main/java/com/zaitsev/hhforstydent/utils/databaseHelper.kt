package com.zaitsev.hhforstydent.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var UID: String

//lateinit var USER: User

const val NODE_USERS = "User"
const val NODE_PLACES = "Place"
const val CHILD_EMAIL = "email"

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    UID = AUTH.currentUser?.uid.toString()
    //USER = User()
}

//Initial users
//fun initUser() {
//    Firebase.firestore.collection(NODE_USERS).document(UID)
//        .addSnapshotListener{ snapshot, e ->
//            if (e != null) {
//                Log.w(TAG, "Listen failed.", e)
//                return@addSnapshotListener
//            }
//
//            if (snapshot != null && snapshot.exists()) {
//                USER = snapshot.getData()
//                Log.d(TAG, "Current data: ${snapshot.data}")
//            } else {
//                Log.d(TAG, "Current data: null")
//            }
//
//        }
//}
