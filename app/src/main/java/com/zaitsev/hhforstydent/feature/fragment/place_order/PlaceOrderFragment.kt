package com.zaitsev.hhforstydent.feature.fragment.place_order

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
import com.zaitsev.hhforstydent.databinding.FragmentPlaceOrderBinding
import com.zaitsev.hhforstydent.utils.APP_ACTIVITY
import com.zaitsev.hhforstydent.utils.NODE_ORDER

class PlaceOrderFragment : BaseFragment(R.layout.fragment_place_order) {

    private val viewBinding by viewBinding(FragmentPlaceOrderBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderArrayList: ArrayList<PlaceOrder>
    private lateinit var adapter: PlaceOrderAdapter
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() = with(viewBinding) {
        recyclerView = orderPlaceRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        orderArrayList = arrayListOf()

        adapter = PlaceOrderAdapter(orderArrayList)

        recyclerView.adapter = adapter

        listener()
    }

    private fun listener() {
        db = FirebaseFirestore.getInstance()
        db.collection(NODE_ORDER).document("AA0bPT9AaPhmCwUTzjLtJISxRpA3").collection("AA0bPT9AaPhmCwUTzjLtJISxRpA3")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Error", error.toString())
                    return@addSnapshotListener
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        orderArrayList.add(dc.document.toObject(PlaceOrder::class.java))
                    }
                }

                adapter.notifyDataSetChanged()

            }
    }
}