package com.example.examencl3dam1.ServicioREST

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {
    //https://quotable.io/quotes?page=1

    //SERVICIO REST:
    //DOMINIO => //SERVICIO
    //WWW.GOOGLE.COM => //Maps
    //https://quotable.io => //quotes?page=1
    @GET("videos.json")
    suspend fun getQuotes(): Response<QuotesList>
}