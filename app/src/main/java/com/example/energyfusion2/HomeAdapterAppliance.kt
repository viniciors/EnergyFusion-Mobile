package com.example.energyfusion2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeApplianceAdapter(
    private val appliances: List<ApplianceIcon>,
    private val onItemClick: (ApplianceIcon) -> Unit
) : RecyclerView.Adapter<HomeApplianceAdapter.HomeApplianceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeApplianceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appliance_home, parent, false)
        return HomeApplianceViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeApplianceViewHolder, position: Int) {
        val appliance = appliances[position]
        holder.bind(appliance)
        holder.itemView.setOnClickListener { onItemClick(appliance) }
    }

    override fun getItemCount(): Int = appliances.size

    class HomeApplianceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon_image)
        private val iconTextView: TextView = itemView.findViewById(R.id.icon_text)

        fun bind(appliance: ApplianceIcon) {
            iconImageView.setImageResource(appliance.iconResId)
            iconTextView.text = appliance.name
        }
    }
}
