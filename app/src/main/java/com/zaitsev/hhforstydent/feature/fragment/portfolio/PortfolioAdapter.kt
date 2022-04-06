package com.zaitsev.hhforstydent.feature.fragment.portfolio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.utils.setImageToImageView

class PortfolioAdapter(private var portfolioList: ArrayList<Portfolio>) :
    RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {

    class PortfolioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.textViewNamePortfolio)
        val description: TextView? = itemView.findViewById(R.id.textViewDescriptionPortfolio)
        val git: TextView? = itemView.findViewById(R.id.textViewGitPortfolio)
        val imageUrl: ImageView? = itemView.findViewById(R.id.circleImageViewPortfolio)
        //val item: CardView? = itemView.findViewById(R.id.circleImageViewPortfolio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_portfolio, parent, false)
        return PortfolioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val portfolio: Portfolio = portfolioList[position]
        holder.name?.text = portfolio.Name
        holder.description?.text = portfolio.Description
        holder.git?.text = portfolio.Git
        setImageToImageView(portfolio.Image, holder.imageUrl!!)
    }

    override fun getItemCount(): Int {
        return portfolioList.size
    }

}