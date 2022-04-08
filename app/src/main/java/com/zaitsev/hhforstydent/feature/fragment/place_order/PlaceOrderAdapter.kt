package com.zaitsev.hhforstydent.feature.fragment.place_order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R

class PlaceOrderAdapter(private var placeOrderList: List<PlaceOrder>) :
    RecyclerView.Adapter<PlaceOrderAdapter.PlaceOrderViewHolder>() {

    class PlaceOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.textViewNameProfileUser)
        val stack: TextView? = itemView.findViewById(R.id.textViewStackProfileUser)
        val startDate: TextView? = itemView.findViewById(R.id.textViewStartDateProfileUser)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceOrderViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_user, parent, false)
        return PlaceOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaceOrderViewHolder, position: Int) {
        val placeOrder: PlaceOrder = placeOrderList[position]
        holder.name?.text = placeOrder.Name
        holder.stack?.text = placeOrder.Stack
        holder.startDate?.text = placeOrder.StartDate
    }

    override fun getItemCount(): Int {
        return placeOrderList.size
    }

}