package com.example.energyfusion2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_appliances)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Lista dos eletrodomésticos
        val appliances = listOf(
            ApplianceIcon("Geladeira", R.drawable.ic_refrigerator),
            ApplianceIcon("Ventilador", R.drawable.ic_fan),
            ApplianceIcon("Cafeteira", R.drawable.ic_coffee_maker),
            ApplianceIcon("Microondas", R.drawable.ic_microwave),
            ApplianceIcon("Lavadora", R.drawable.ic_washing_machine)
        )

        //Configuração do Adapter
        val adapter = HomeApplianceAdapter(appliances) { appliance ->
            when (appliance.name) {
                "Geladeira" -> startActivity(Intent(this, RefrigeratorActivity::class.java))
                "Ventilador" -> startActivity(Intent(this, FanActivity::class.java))
                "Cafeteira" -> startActivity(Intent(this, CoffeeMakerActivity::class.java))
                "Microondas" -> startActivity(Intent(this, MicrowaveActivity::class.java))
                "Lavadora" -> startActivity(Intent(this, WashingMachineActivity::class.java))
            }
        }
        recyclerView.adapter = adapter
    }
}
