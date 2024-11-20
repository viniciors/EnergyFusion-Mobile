package com.example.energyfusion2

data class Appliance(
    val id: String = "", // ID do documento Firestore
    val name: String = "",
    val efficiency: String = "",
    val consumption: Double = 0.0,
    val price: Double = 0.0
)

