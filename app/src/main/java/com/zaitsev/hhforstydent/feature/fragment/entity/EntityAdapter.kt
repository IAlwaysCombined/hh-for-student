package com.zaitsev.hhforstydent.feature.fragment.entity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R

class EntityAdapter: RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_company, parent, false)
        return EntityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}