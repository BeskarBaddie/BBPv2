package com.honours.bbpv2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.148:8000/"


    //this is where you create functions for each API endpoint so you can access it
    interface ApiInterface {
        @GET("api/users/")
        fun getData(): Call<DataResponse>

        @GET("api/video/")
        fun getVideos(): Call<VideoResponse>
    }

    fun getApiInterface(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}