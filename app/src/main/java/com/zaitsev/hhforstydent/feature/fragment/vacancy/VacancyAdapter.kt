package com.zaitsev.hhforstydent.feature.fragment.vacancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R

class VacancyAdapter(private var vacancyList: List<Vacancy>): RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {

    class VacancyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val vacancy: TextView = itemView.findViewById(R.id.textViewVacancyName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_vacancy, parent, false)
        return VacancyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy: Vacancy = vacancyList[position]
        holder.vacancy.text = vacancy.Vacancy
    }

    override fun getItemCount(): Int {
        return vacancyList.size
    }

}