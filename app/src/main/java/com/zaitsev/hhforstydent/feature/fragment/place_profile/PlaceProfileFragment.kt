package com.zaitsev.hhforstydent.feature.fragment.place_profile

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentPlaceAuthBinding
import com.zaitsev.hhforstydent.databinding.FragmentPlaceProfileBinding
import com.zaitsev.hhforstydent.feature.activity.AuthActivity
import com.zaitsev.hhforstydent.utils.*


class PlaceProfileFragment : BaseFragment(R.layout.fragment_place_profile) {

    private val viewBinding by viewBinding(FragmentPlaceProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initButtons()
    }

    @SuppressLint("SetTextI18n")
    fun initObserver() = with(viewBinding){
        val db = Firebase.firestore
        val uid = AUTH.currentUser?.uid.toString()
        db.collection(NODE_PLACES).document(uid).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val name = snapshot.getString("Name")
                val description = snapshot.getString("Description")
                val address = snapshot.getString("Address")
                val image = snapshot.getString("Image_url")


                textViewPlaceName.text = "Название: " + name.toString()
                textViewPlaceDescription.text = "Описание: " + description.toString()
                textViewPlaceAddress.text = "Адрес: " + address.toString()
                setImageToImageView(image.toString(), imageViewPlaceProfile)
                Log.d(ContentValues.TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(ContentValues.TAG, "Current data: null")
            }
        }
    }

    fun initButtons() = with(viewBinding) {
        buttonExitPlace.setOnClickListener {
            AUTH.signOut()
            replaceActivity(AuthActivity())
        }
    }

}