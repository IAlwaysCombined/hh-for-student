package com.zaitsev.hhforstydent.feature.fragment.like

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.utils.setImageToImageView

class LikeAdapter(private var likeList: ArrayList<Like>) :
    RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    class LikeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.textViewNameCompany)
        val description: TextView? = itemView.findViewById(R.id.textViewAboutCompany)
        val imageUrl: ImageView? = itemView.findViewById(R.id.imageViewCompany)
        val item: CardView? = itemView.findViewById(R.id.itemCompany)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_company, parent, false)
        return LikeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val like: Like = likeList[position]
        holder.name?.text = like.Name
        holder.description?.text = like.Description
        setImageToImageView(like.Image_url, holder.imageUrl!!)
    }

    override fun getItemCount(): Int {
        return likeList.size
    }

}