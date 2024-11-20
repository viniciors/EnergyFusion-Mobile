package com.example.energyfusion2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class FanActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fanAdapter: ApplianceAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recycler_view_fans)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fanAdapter = ApplianceAdapter(emptyList()) { appliance ->
            openDetailActivity(appliance)
        }
        recyclerView.adapter = fanAdapter

        db = FirebaseFirestore.getInstance()

        val btnAddFan = findViewById<Button>(R.id.btn_add_fan)
        btnAddFan.setOnClickListener {
            showAddFanDialog()
        }

        loadFans()
    }

    private fun openDetailActivity(appliance: Appliance) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("applianceId", appliance.id)
            putExtra("name", appliance.name)
            putExtra("efficiency", appliance.efficiency)
            putExtra("consumption", appliance.consumption)
            putExtra("price", appliance.price)
        }
        startActivity(intent)
    }

    private fun showAddFanDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_appliance, null)
        val etName = dialogView.findViewById<EditText>(R.id.et_name)
        val etEfficiency = dialogView.findViewById<EditText>(R.id.et_efficiency)
        val etConsumption = dialogView.findViewById<EditText>(R.id.et_consumption)
        val etPrice = dialogView.findViewById<EditText>(R.id.et_price)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Adicionar Ventilador")
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            val name = etName.text.toString()
            val efficiency = etEfficiency.text.toString()
            val consumption = etConsumption.text.toString().toDoubleOrNull() ?: 0.0
            val price = etPrice.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotBlank() && efficiency.isNotBlank()) {
                addFanToFirebase(name, efficiency, consumption, price)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss() // Fecha o diálogo sem salvar
        }

        dialog.show()
    }

    private fun addFanToFirebase(name: String, efficiency: String, consumption: Double, price: Double) {
        val fan = hashMapOf(
            "name" to name,
            "efficiency" to efficiency,
            "consumption" to consumption,
            "price" to price,
            "type" to "Fan"
        )

        db.collection("appliances")
            .add(fan)
            .addOnSuccessListener {
                Toast.makeText(this, "Ventilador adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                loadFans() // Atualizar lista
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao adicionar ventilador", Toast.LENGTH_SHORT).show()
            }
    }

    private fun loadFans() {
        db.collection("appliances")
            .whereEqualTo("type", "Fan")
            .get()
            .addOnSuccessListener { result ->
                val fans = result.map { document ->
                    Appliance(
                        id = document.id,
                        name = document.getString("name") ?: "",
                        efficiency = document.getString("efficiency") ?: "",
                        consumption = document.getDouble("consumption") ?: 0.0,
                        price = document.getDouble("price") ?: 0.0
                    )
                }
                fanAdapter.updateData(fans)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao carregar ventiladores", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
