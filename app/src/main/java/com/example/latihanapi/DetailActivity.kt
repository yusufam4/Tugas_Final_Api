package com.example.latihanapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.title = intent.getStringExtra("intent_title")
        Glide.with(this)
            .load(intent.getStringExtra("intent_thumb"))
            .placeholder(R.drawable.img_placeholder)
            .into(imageView)

        buttonShare.setOnClickListener {
            shareAction()
        }
    }

    private fun shareAction() {

        val text = "https://www.masakapahariini.com/resep/" + intent.getStringExtra("intent_key")

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


}