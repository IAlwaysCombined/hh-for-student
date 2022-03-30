package com.zaitsev.hhforstydent.feature.fragment.entity

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
import com.zaitsev.hhforstydent.databinding.FragmentEntityBinding
import com.zaitsev.hhforstydent.utils.APP_ACTIVITY
import com.zaitsev.hhforstydent.utils.NODE_PLACES

class EntityFragment : BaseFragment(R.layout.fragment_entity) {

    private val viewBinding by viewBinding(FragmentEntityBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var entityArrayList: ArrayList<Entity>
    private lateinit var adapter: EntityAdapter
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initAdapter()
    }

    private fun initAdapter() = with(viewBinding) {
        recyclerView = recyclerViewEntity
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        entityArrayList = arrayListOf()

        adapter = EntityAdapter(entityArrayList)

        recyclerView.adapter = adapter

        listener()

    }

    private fun listener() {
        db = FirebaseFirestore.getInstance()
        db.collection(NODE_PLACES)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Error", error.toString())
                    return@addSnapshotListener
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        entityArrayList.add(dc.document.toObject(Entity::class.java))
                    }
                }

                adapter.notifyDataSetChanged()

            }
    }

    private fun initButtons() = with(viewBinding) {

    }

}