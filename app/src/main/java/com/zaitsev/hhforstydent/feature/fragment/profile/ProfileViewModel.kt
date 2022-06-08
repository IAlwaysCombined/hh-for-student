package com.zaitsev.hhforstydent.feature.fragment.profile

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.utils.AUTH
import com.zaitsev.hhforstydent.utils.NODE_USERS

class ProfileViewModel() : ViewModel() {

    fun getProfile() {
        val db = Firebase.firestore
        val uid = AUTH.currentUser?.uid.toString()
        db.collection(NODE_USERS).document(uid).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {

                val name = snapshot.getString("Name")
                val course = snapshot.getString("Well")
                val surname = snapshot.getString("Surname")
                val patronymic = snapshot.getString("Middle_name")
                val special = snapshot.getString("Spec")
                val stack = snapshot.getString("Stack")
                val languages = snapshot.getString("Language")
                val image = snapshot.getString("ImageURL")

                val map = mapOf(
                    "name" to name,
                    "course" to course,
                    "surname" to surname,
                    "patronymic" to patronymic,
                    "special" to special,
                    "stack" to stack,
                    "languages" to languages,
                    "image" to image
                )

            } else {
                Log.d(ContentValues.TAG, "Current data: null")
            }
        }
    }

}