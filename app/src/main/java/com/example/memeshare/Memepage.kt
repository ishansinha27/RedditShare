package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.memeshare.databinding.ActivityMemepageBinding
import org.json.JSONObject

lateinit var binding:ActivityMemepageBinding
class memepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMemepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.sharebtn.setOnClickListener{
            sharememe()
        }

        var memeImage = findViewById<ImageView>(R.id.meme)
        loadmeme()
        binding.nextbtn.setOnClickListener{
            loadmeme()
        }


    }

    fun loadmeme(){

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->

               var reasonObject = JSONObject(response)
                Glide.with(this).load(reasonObject.get("url")).into(binding.meme)
            },
            {

            })


        queue.add(stringRequest)

    }
    val urlapi = "https://meme-api.com/gimme"
    fun sharememe(){
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey! See what I found on reddit : $urlapi")
        val chooser = Intent.createChooser(intent,"Share this meme using")
        startActivity(chooser)
    }
}


