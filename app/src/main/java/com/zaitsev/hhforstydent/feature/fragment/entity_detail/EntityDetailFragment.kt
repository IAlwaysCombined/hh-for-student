package com.zaitsev.hhforstydent.feature.fragment.entity_detail

import android.content.ContentValues
import android.os.Build.ID
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentEntityDetailBinding
import com.zaitsev.hhforstydent.utils.*


class EntityDetailFragment: BaseFragment(R.layout.fragment_entity_detail) {

    private val viewBinding by viewBinding(FragmentEntityDetailBinding::bind)
    val bundle = Bundle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() = with(viewBinding) {
        val db = Firebase.firestore
        val uid = AUTH.currentUser?.uid
        val email = AUTH.currentUser?.email
        val id = arguments?.getString("arg").toString()
        Log.d("ID Bundle", id)
        db.collection(NODE_PLACES).document(id).addSnapshotListener { snapshot, e ->
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
                val description = snapshot.getString("Description")

                val idPlace = snapshot.getString("ID")

                textViewCompanyNameDetail.text = name.toString()
                textViewAddress.text = address.toString()
                textViewPhone.text = phone.toString()
                textViewSupervisor.text = supervisor.toString()
                textViewWeb.text = web.toString()
                textViewDescription.text = description.toString()

                setImageToImageView(image.toString(), imageViewCompanyDetail)

                buttonVacancy.setOnClickListener {
                    bundle.putString("arg", idPlace.toString())
                    findNavController().navigate(R.id.action_entityFragment_to_vacancyFragment, bundle)
                }

                buttonPutOrder.setOnClickListener {
                    val dateMap = mutableMapOf<String, Any>()

                    val language = snapshot.getString("Language")
                    val middleName = snapshot.getString("Middle_Name")

                    dateMap[CHILD_COMPANY_NAME] = name.toString()
                    dateMap[CHILD_EMAIL] = email.toString()
                    dateMap[CHILD_END_DATE] = "30.04.2022"
                    dateMap[CHILD_IMAGE] = "https://firebasestorage.googleapis.com:443/v0/b/hhstudnet.appspot.com/o/User%2F191656?alt=media&token=2c77c454-4891-45cb-be6c-5478c952a2db"
                    dateMap[CHILD_LANGUAGE] = "Swift,Kotlin,Dart"
                    dateMap[CHILD_MIDDLE_NAME] = "Андреевич"
                    dateMap[CHILD_NAME] = "Матвей"
                    dateMap[CHILD_ORDER_USER] = "Принят"
                    dateMap[CHILD_SPECIAL] = "Информационные системы и программирование"
                    dateMap[CHILD_STACK] = "Flutter,SwiftUI,Jetpack Compose"
                    dateMap[CHILD_START_DATE] = "12.02.2022"
                    dateMap[CHILD_SURNAME] = "Ганин"
                    dateMap[CHILD_VACANCY] = "Программист"
                    dateMap[CHILD_COURSE] = "4"
                    db.collection(NODE_ORDER).document(uid.toString()).set(dateMap)
                    showBaseSnackBar("Заявка оставлена")
                }

            }
        }
    }

}