package com.example.energyfusion2

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class ApplianceRepository {

    private val db = FirebaseFirestore.getInstance()

    //Adiciona um eletrodoméstico
    fun addAppliance(name: String, efficiency: String, consumption: Double, price: Double) {
        val appliance = hashMapOf(
            "name" to name,
            "efficiency" to efficiency,
            "consumption" to consumption,
            "price" to price
        )

        db.collection("appliances")
            .add(appliance)
            .addOnSuccessListener { documentReference ->
                Log.d("Firebase", "Eletrodoméstico adicionado com ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firebase", "Erro ao adicionar eletrodoméstico", e)
            }
    }

    //Lista os eletrodomésticos
    fun listAppliances(callback: (List<Appliance>) -> Unit) {
        db.collection("appliances")
            .get()
            .addOnSuccessListener { result ->
                val appliances = result.documents.mapNotNull { it.toObject<Appliance>() }
                callback(appliances)
            }
            .addOnFailureListener { e ->
                Log.w("Firebase", "Erro ao listar eletrodomésticos", e)
                callback(emptyList()) // Retorna uma lista vazia em caso de erro
            }
    }

    //Atualiza um eletrodoméstico
    fun updateAppliance(id: String, name: String, efficiency: String, consumption: Double, price: Double) {
        val applianceUpdates = hashMapOf(
            "name" to name,
            "efficiency" to efficiency,
            "consumption" to consumption,
            "price" to price
        )

        db.collection("appliances").document(id)
            .update(applianceUpdates as Map<String, Any>)
            .addOnSuccessListener {
                Log.d("Firebase", "Eletrodoméstico atualizado com sucesso!")
            }
            .addOnFailureListener { e ->
                Log.w("Firebase", "Erro ao atualizar eletrodoméstico", e)
            }
    }

    //Exclui um eletrodoméstico
    fun deleteAppliance(id: String) {
        db.collection("appliances").document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("Firebase", "Eletrodoméstico excluído com sucesso!")
            }
            .addOnFailureListener { e ->
                Log.w("Firebase", "Erro ao excluir eletrodoméstico", e)
            }
    }
}
