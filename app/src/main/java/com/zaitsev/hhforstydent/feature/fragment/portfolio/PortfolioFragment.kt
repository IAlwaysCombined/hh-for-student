package com.zaitsev.hhforstydent.feature.fragment.portfolio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.core.BaseFragment
import com.zaitsev.hhforstydent.databinding.FragmentLikeBinding
import com.zaitsev.hhforstydent.databinding.FragmentPortfolioBinding
import com.zaitsev.hhforstydent.feature.fragment.like.Like
import com.zaitsev.hhforstydent.feature.fragment.like.LikeAdapter
import com.zaitsev.hhforstydent.utils.*


class PortfolioFragment : BaseFragment(R.layout.fragment_portfolio) {

    private val viewBinding by viewBinding(FragmentPortfolioBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var portfolioArrayList: ArrayList<Portfolio>
    private lateinit var adapter: PortfolioAdapter
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() = with(viewBinding) {
        recyclerView = recyclerViewPortfolio
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        portfolioArrayList = arrayListOf()

        adapter = PortfolioAdapter(portfolioArrayList)

        recyclerView.adapter = adapter

        listener()

    }

    private fun listener(){
        db = FirebaseFirestore.getInstance()

        db.collection(NODE_USERS).document(UID).collection(NODE_PORTFOLIO)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Error", error.toString())
                    return@addSnapshotListener
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        portfolioArrayList.add(dc.document.toObject(Portfolio::class.java))
                    }
                }

                adapter.notifyDataSetChanged()

            }
    }

}