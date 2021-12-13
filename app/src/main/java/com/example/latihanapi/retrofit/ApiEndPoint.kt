package com.example.latihanapi.retrofit

import com.example.latihanapi.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("recipes")
    fun getRecipes(): Call<MainModel>
}