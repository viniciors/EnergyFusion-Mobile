package com.example.energyfusion2

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var applianceId: String
    private lateinit var etName: EditText
    private lateinit var etEfficiency: EditText
    private lateinit var etConsumption: EditText
    private lateinit var etPrice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etName = findViewById(R.id.et_name)
        etEfficiency = findViewById(R.id.et_efficiency)
        etConsumption = findViewById(R.id.et_consumption)
        etPrice = findViewById(R.id.et_price)

        db = FirebaseFirestore.getInstance()

        applianceId = intent.getStringExtra("applianceId") ?: ""
        etName.setText(intent.getStringExtra("name") ?: "")
        etEfficiency.setText(intent.getStringExtra("efficiency") ?: "")
        etConsumption.setText(intent.getDoubleExtra("consumption", 0.0).toString())
        etPrice.setText(intent.getDoubleExtra("price", 0.0).toString())

        findViewById<Button>(R.id.btn_update).setOnClickListener {
            updateAppliance()
        }

        findViewById<Button>(R.id.btn_delete).setOnClickListener {
            deleteAppliance()
        }
    }

    private fun updateAppliance() {
        val updatedData = mapOf(
            "name" to etName.text.toString(),
            "efficiency" to etEfficiency.text.toString(),
            "consumption" to (etConsumption.text.toString().toDoubleOrNull() ?: 0.0),
            "price" to (etPrice.text.toString().toDoubleOrNull() ?: 0.0)
        )

        db.collection("appliances").document(applianceId)
            .update(updatedData)
            .addOnSuccessListener {
                Toast.makeText(this, "Eletrodoméstico atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao atualizar eletrodoméstico", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteAppliance() {
        db.collection("appliances").document(applianceId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Eletrodoméstico excluído com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao excluir eletrodoméstico", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish() // Retorna à tela anterior
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
