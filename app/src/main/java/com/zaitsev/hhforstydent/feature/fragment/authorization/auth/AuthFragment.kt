package com.zaitsev.hhforstydent.feature.fragment.authorization.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.MainActivity
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentAuthBinding
import com.zaitsev.hhforstydent.utils.*

class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    private val viewBinding by viewBinding(FragmentAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() = with(viewBinding) {
        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        buttonAuthorization.setOnClickListener {
            if (
                editTextLogin.text.toString().isNotEmpty() &&
                editTextPassword.text.toString().isNotEmpty()
            ) {
                AUTH.signInWithEmailAndPassword(
                    editTextLogin.text.toString(),
                    editTextPassword.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showBaseSnackBar("Вы авторизовались!")
                        replaceActivity(MainActivity())
                    } else showBaseSnackBar("Что то пошло не так.")
                }
            } else showBaseSnackBar("Заполните поля!")
        }
        buttonAuthGoogle.setOnClickListener {

        }
    }

}