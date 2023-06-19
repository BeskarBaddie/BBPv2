package com.honours.bbpv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PublicArticles : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_articles)





        // Create the Retrofit API interface
        val apiInterface = RetrofitClient.getApiInterface()

        // Make the API call
        val call: Call<DataResponse> = apiInterface.getData()
        call.enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    val data: DataResponse? = response.body()
                    if (data != null) {
                        updateTextView(data)
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("PublicArticles", "API call failed with response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                // Handle network failure
                Log.e("PublicArticles", "API call failed: ${t.message}")
            }
        })

    }

    private fun updateTextView(data: DataResponse) {
        val dataTextView: TextView = findViewById(R.id.dataTextView)
        val text = "Name: ${data.firstname}\nPassword: ${data.password}"
        dataTextView.text = text
    }


}