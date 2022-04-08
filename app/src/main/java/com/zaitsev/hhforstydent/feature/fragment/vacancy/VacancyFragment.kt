package com.zaitsev.hhforstydent.feature.fragment.vacancy

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
import com.zaitsev.hhforstydent.databinding.FragmentEntityDetailBinding
import com.zaitsev.hhforstydent.databinding.FragmentVacancyBinding
import com.zaitsev.hhforstydent.feature.fragment.portfolio.Portfolio
import com.zaitsev.hhforstydent.feature.fragment.portfolio.PortfolioAdapter
import com.zaitsev.hhforstydent.utils.*


class VacancyFragment : BaseFragment(R.layout.fragment_vacancy) {

    private val viewBinding by viewBinding(FragmentVacancyBinding::bind)

    private lateinit var recyclerView: RecyclerView
    private lateinit var vacancyArrayList: ArrayList<Vacancy>
    private lateinit var adapter: VacancyAdapter
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() = with(viewBinding){
        recyclerView = vacancyRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.setHasFixedSize(true)

        vacancyArrayList = arrayListOf()

        adapter = VacancyAdapter(vacancyArrayList)

        recyclerView.adapter = adapter

        listener()
    }

    private fun listener() {
        db = FirebaseFirestore.getInstance()
        val id = arguments?.getString("arg")
        db.collection(NODE_PLACES).document(id.toString()).collection(NODE_VACANCY)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("Error", error.toString())
                    return@addSnapshotListener
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        vacancyArrayList.add(dc.document.toObject(Vacancy::class.java))
                    }
                }

                adapter.notifyDataSetChanged()

            }
    }

}