package com.zaitsev.hhforstydent.feature.fragment.entity_detail

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentEntityDetailBinding
import com.zaitsev.hhforstydent.utils.AUTH
import com.zaitsev.hhforstydent.utils.NODE_USERS
import com.zaitsev.hhforstydent.utils.setImageToImageView


class EntityDetailFragment : BaseFragment(R.layout.fragment_entity_detail) {

    private val viewBinding by viewBinding(FragmentEntityDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        initButtons()
    }

    private fun initButtons() = with(viewBinding) {
        buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        buttonPutOrder.setOnClickListener {

        }
    }

    private fun initObserve() = with(viewBinding) {
        val db = Firebase.firestore
        val uid = AUTH.currentUser?.uid.toString()
        db.collection(NODE_USERS).document(uid).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val name = snapshot.getString("Name")
                val address = snapshot.getString("Address")
                val image = snapshot.getString("Image_url")
                val phone = snapshot.getString("Phone")
                val supervisor = snapshot.getString("Supervisor")
                val web = snapshot.getString("Web")

                textViewCompanyNameDetail.text = name.toString()
                textViewAddress.text = address.toString()
                textViewPhone.text = phone.toString()
                textViewSupervisor.text = supervisor.toString()
                textViewWeb.text = web.toString()

                setImageToImageView(image.toString(), imageViewCompanyDetail)
            }
        }
    }

}