package com.example.examencl3dam1.ServicioREST

data class Result (
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
)