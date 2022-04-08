package com.zaitsev.hhforstydent.feature.fragment.like

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
import com.zaitsev.hhforstydent.databinding.FragmentLikeBinding
import com.zaitsev.hhforstydent.utils.APP_ACTIVITY
import com.zaitsev.hhforstydent.utils.NODE_PLACES
import com.zaitsev.hhforstydent.utils.NODE_PLACES_LIKE
import com.zaitsev.hhforstydent.utils.UID

class LikeFragment : BaseFragment(R.layout.fragment_like) {

    private val viewBinding by viewBinding(FragmentLikeBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var likeArrayList: ArrayList<Like>
    private lateinit var adapter: LikeAdapter
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter()  = with(viewBinding) {
        recyclerView = recyclerviewLike
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        likeArrayList = arrayListOf()

        adapter = LikeAdapter(likeArrayList)

        recyclerView.adapter = adapter

        listener()
    }

    private fun listener() {
        db = FirebaseFirestore.getInstance()
        db.collection(NODE_PLACES_LIKE).document(UID).collection(NODE_PLACES)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Error", error.toString())
                    return@addSnapshotListener
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        likeArrayList.add(dc.document.toObject(Like::class.java))
                    }
                }

                adapter.notifyDataSetChanged()

            }
    }

}