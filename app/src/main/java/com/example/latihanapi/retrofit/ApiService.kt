package com.example.latihanapi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    val BASE_URL : String = "https://masak-apa.tomorisakura.vercel.app/api/"
    val endpoint : ApiEndPoint
    get(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(ApiEndPoint::class.java)
    }
}