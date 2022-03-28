package com.zaitsev.hhforstydent.feature.fragment.authorization.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.MainActivity
import com.zaitsev.hhforstydent.databinding.FragmentRegistrationBinding
import com.zaitsev.hhforstydent.utils.*


class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val viewBinding by viewBinding(FragmentRegistrationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() = with(viewBinding) {
        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        buttonRegistration.setOnClickListener {
            if (editTextLogin.text.toString().isNotEmpty() &&
                editTextPassword.text.toString().isNotEmpty() &&
                editTextRePassword.text.toString().isNotEmpty()
            ) {
                AUTH.createUserWithEmailAndPassword(
                    editTextLogin.text.toString(),
                    editTextPassword.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val db = Firebase.firestore
                            val dateMap = mutableMapOf<String, Any>()
                            val email = AUTH.currentUser?.email.toString()
                            val uid = AUTH.currentUser?.uid.toString()
                            dateMap[CHILD_EMAIL] = email
                            db.collection(NODE_USERS).document(uid).set(dateMap)
                            showBaseSnackBar("Вы зарегистрировались!")
                            replaceActivity(MainActivity())

                        } else showBaseSnackBar("Что то пошло не так.")
                    }
            } else showBaseSnackBar("Заполните поля!")
        }
    }

}