package com.example.energyfusion2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplianceIconAdapter(
    private val appliances: List<ApplianceIcon>,
    private val onItemClick: (ApplianceIcon) -> Unit
) : RecyclerView.Adapter<ApplianceIconAdapter.ApplianceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplianceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appliance, parent, false)
        return ApplianceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplianceViewHolder, position: Int) {
        val appliance = appliances[position]
        holder.bind(appliance)
        holder.itemView.setOnClickListener { onItemClick(appliance) }
    }

    override fun getItemCount(): Int = appliances.size

    class ApplianceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon_image)
        private val iconTextView: TextView = itemView.findViewById(R.id.icon_text)

        fun bind(appliance: ApplianceIcon) {
            iconImageView.setImageResource(appliance.iconResId)
            iconTextView.text = appliance.name
        }
    }
}

data class ApplianceIcon(val name: String, val iconResId: Int)
