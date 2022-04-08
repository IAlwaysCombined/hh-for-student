package com.zaitsev.hhforstydent.feature.fragment.profile

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentProfileBinding
import com.zaitsev.hhforstydent.feature.activity.AuthActivity
import com.zaitsev.hhforstydent.utils.*


class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewBinding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initButtons()
        initObserve()
    }

    @SuppressLint("SetTextI18n")
    private fun initObserve() = with(viewBinding) {
        val db = Firebase.firestore
        val uid = AUTH.currentUser?.uid.toString()
        db.collection(NODE_USERS).document(uid).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
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


                textViewName.text = "Имя: " + name.toString()
                textViewCourse.text = "Курс: " + course.toString()
                textViewSurname.text = "Отчество: " + surname.toString()
                textViewPatronymic.text = "Фамилия: " + patronymic.toString()
                textViewSpecial.text = "Специальность: " + special.toString()
                textViewProgramLanguage.text = "Языки: " + languages.toString()
                textViewStack.text = "Стэк: " + stack.toString()
                setImageToImageView(image.toString(), imageViewProfile)
                Log.d(TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun initButtons() = with(viewBinding) {
        buttonExit.setOnClickListener {
            AUTH.signOut()
            replaceActivity(AuthActivity())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_nav_menu, menu)
    }

}