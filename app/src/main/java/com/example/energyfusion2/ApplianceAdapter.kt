package com.example.energyfusion2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplianceAdapter(
    private var appliances: List<Appliance>,
    private val onItemClick: (Appliance) -> Unit
) : RecyclerView.Adapter<ApplianceAdapter.ApplianceViewHolder>() {

    // ViewHolder que representa cada item na lista
    class ApplianceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.icon_image)
        val nameTextView: TextView = itemView.findViewById(R.id.icon_text)
        val efficiencyTextView: TextView = itemView.findViewById(R.id.tv_efficiency)
        val consumptionTextView: TextView = itemView.findViewById(R.id.tv_consumption)
        val priceTextView: TextView = itemView.findViewById(R.id.tv_price)

        fun bind(appliance: Appliance, onItemClick: (Appliance) -> Unit) {
            nameTextView.text = appliance.name
            efficiencyTextView.text = "Eficiência: ${appliance.efficiency}"
            consumptionTextView.text = "Consumo: ${appliance.consumption} kWh"
            priceTextView.text = "Preço: R$${appliance.price}"

            itemView.setOnClickListener { onItemClick(appliance) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplianceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appliance, parent, false)
        return ApplianceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplianceViewHolder, position: Int) {
        holder.bind(appliances[position], onItemClick)
    }

    override fun getItemCount(): Int = appliances.size

    //Atualiza a lista de eletrodomésticos
    fun updateData(newAppliances: List<Appliance>) {
        appliances = newAppliances
        notifyDataSetChanged()
    }
}
