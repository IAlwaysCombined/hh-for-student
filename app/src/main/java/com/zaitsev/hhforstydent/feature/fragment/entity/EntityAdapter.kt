package com.zaitsev.hhforstydent.feature.fragment.entity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.feature.fragment.entity_detail.EntityDetailFragment
import com.zaitsev.hhforstydent.utils.APP_ACTIVITY
import com.zaitsev.hhforstydent.utils.setImageToImageView

class EntityAdapter(private var entityList: ArrayList<Entity>) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    val bundle = Bundle()

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.textViewNameCompany)
        val description: TextView? = itemView.findViewById(R.id.textViewAboutCompany)
        val imageUrl: ImageView? = itemView.findViewById(R.id.imageViewCompany)
        val item: CardView? = itemView.findViewById(R.id.itemCompany)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_company, parent, false)
        return EntityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity: Entity = entityList[position]
        holder.name?.text = entity.Name
        holder.description?.text = entity.Description
        setImageToImageView(entity.Image_url, holder.imageUrl!!)
        Log.d("Id place", entity.ID)
        holder.item?.setOnClickListener{ view ->
            bundle.putString("arg", entityList[position].ID)
            Log.d("Bundle", bundle.toString())
            view.findNavController().navigate(R.id.action_entityFragment_to_entityDetailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return entityList.size
    }
}