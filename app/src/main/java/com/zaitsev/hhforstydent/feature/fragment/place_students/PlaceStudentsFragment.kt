package com.zaitsev.hhforstydent.feature.fragment.place_students

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentPlaceAuthBinding
import com.zaitsev.hhforstydent.databinding.FragmentPlaceStudentsBinding
import com.zaitsev.hhforstydent.feature.fragment.like.Like
import com.zaitsev.hhforstydent.feature.fragment.like.LikeAdapter
import com.zaitsev.hhforstydent.utils.*


class PlaceStudentsFragment : BaseFragment(R.layout.fragment_place_students) {

    private val viewBinding by viewBinding(FragmentPlaceStudentsBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var placeStudentsArrayList: ArrayList<PlaceStudents>
    private lateinit var adapter: PlaceStudentsAdapter
    private lateinit var db: FirebaseFirestore


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    fun initAdapter() = with(viewBinding){
        recyclerView = placeStudentRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        placeStudentsArrayList = arrayListOf()

        adapter = PlaceStudentsAdapter(placeStudentsArrayList)

        recyclerView.adapter = adapter

        launch()
    }

    private fun launch(){
        db = FirebaseFirestore.getInstance()
        db.collection(NODE_USERS)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Error", error.toString())
                    return@addSnapshotListener
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        placeStudentsArrayList.add(dc.document.toObject(PlaceStudents::class.java))
                    }
                }

                adapter.notifyDataSetChanged()

            }
    }
}