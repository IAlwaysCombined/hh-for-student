package com.zaitsev.hhforstydent.feature.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentPlaceAuthBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zaitsev.hhforstydent.MainActivity
import com.zaitsev.hhforstydent.utils.AUTH
import com.zaitsev.hhforstydent.utils.replaceActivity
import com.zaitsev.hhforstydent.utils.showBaseSnackBar

class PlaceAuthFragment : BaseFragment(R.layout.fragment_place_auth) {

    private val viewBinding by viewBinding(FragmentPlaceAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() = with(viewBinding) {
        buttonBackPlace.setOnClickListener {
            findNavController().popBackStack()
        }
        buttonAuthPlace.setOnClickListener {
            if (
                editTextLoginPlace.text.toString().isNotEmpty() &&
                editTextPasswordPlace.text.toString().isNotEmpty()
            ) {
                AUTH.signInWithEmailAndPassword(
                    editTextLoginPlace.text.toString(),
                    editTextPasswordPlace.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showBaseSnackBar("Вы авторизовались!")
                        replaceActivity(MainActivity())
                    } else showBaseSnackBar("Что то пошло не так.")
                }
            } else showBaseSnackBar("Заполните поля!")
        }
    }

}