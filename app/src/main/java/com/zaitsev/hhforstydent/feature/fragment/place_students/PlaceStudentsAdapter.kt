package com.zaitsev.hhforstydent.feature.fragment.place_students

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.feature.fragment.like.Like
import com.zaitsev.hhforstydent.feature.fragment.like.LikeAdapter
import com.zaitsev.hhforstydent.utils.setImageToImageView

class PlaceStudentsAdapter(private val placeStudentList: List<PlaceStudents>): RecyclerView.Adapter<PlaceStudentsAdapter.PlaceStudentViewHolder>() {

    class PlaceStudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView? = itemView.findViewById(R.id.textViewPlaceProfileName)
        val image: ImageView? = itemView.findViewById(R.id.circleImageViewPlaceProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceStudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_place_student, parent, false)
        return PlaceStudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaceStudentViewHolder, position: Int) {
        val place: PlaceStudents = placeStudentList[position]
        holder.name?.text = place.Name
        setImageToImageView(place.ImageURL, holder.image!!)
    }

    override fun getItemCount(): Int {
        return placeStudentList.size
    }

}