package com.zaitsev.hhforstydent.feature.fragment.settings

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentSettingsBinding
import com.zaitsev.hhforstydent.utils.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val viewBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initObserver()
    }

    private fun initObserver() = with(viewBinding){
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
                editTextNameEdit.setText(name.toString())
                editTextCourseEdit.setText(course.toString())
                editTextSurnameEdit.setText(surname.toString())
                editTextMiddleNameEdit.setText(patronymic.toString())
                editTextSpecialEdit.setText(special.toString())
                editTextLanguageEdit.setText(languages.toString())
                editTextStackEdit.setText(stack.toString())
                setImageToImageView(image.toString(), imageViewEditImage)
                Log.d(ContentValues.TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(ContentValues.TAG, "Current data: null")
            }
        }
    }

    private fun initButtons() = with(viewBinding){
        buttonSettings.setOnClickListener {
            val db = Firebase.firestore
            val uid = AUTH.currentUser?.uid.toString()
            val dateMap = mutableMapOf<String, Any>()
            dateMap[CHILD_NAME] = editTextNameEdit.text.toString()
            dateMap[CHILD_SURNAME] = editTextSurnameEdit.text.toString()
            dateMap[CHILD_MIDDLE_NAME] = editTextMiddleNameEdit.text.toString()
            dateMap[CHILD_COURSE] = editTextCourseEdit.text.toString()
            dateMap[CHILD_LANGUAGE] = editTextLanguageEdit.text.toString()
            dateMap[CHILD_SPECIAL] = editTextSpecialEdit.text.toString()
            dateMap[CHILD_STACK] = editTextStackEdit.text.toString()
            dateMap[CHILD_UID] = uid
            db.collection(NODE_USERS).document(uid).update(dateMap)
            showBaseSnackBar("Данные успешно обновлены!")
        }

        imageViewEditImage.setOnClickListener {

        }
    }

}