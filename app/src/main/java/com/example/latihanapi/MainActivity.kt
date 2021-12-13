package com.example.latihanapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanapi.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Aplikasi Berbagi Resep"
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.onAdapterListener{
            override fun onClick(result: MainModel.Result) {
                startActivity(
                    Intent(applicationContext, DetailActivity::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("intent_thumb", result.thumb)
                        .putExtra("intent_key", result.key)
                )

            }

        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromApi(){
        progressBar.visibility = View.VISIBLE
        ApiService.endpoint.getRecipes()
            .enqueue(object : Callback<MainModel> {
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful){
                        showRecipes(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    printLog(t.toString())
                }
            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showRecipes(recipes: MainModel){
        val result = recipes.results
        mainAdapter.setData(result)
    }

}