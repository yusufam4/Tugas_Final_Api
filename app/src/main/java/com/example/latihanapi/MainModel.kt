package com.example.latihanapi

data class MainModel (val results: ArrayList<Result>){
    data class Result (
        val key : String,
        val title : String,
        val thumb : String
    )
}

