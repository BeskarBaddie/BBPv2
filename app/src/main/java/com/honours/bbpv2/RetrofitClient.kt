package com.honours.bbpv2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitClient {
    private const val BASE_URL = "http://196.24.166.130:8000/"

    interface ApiInterface {
        @GET("api/users/")
        fun getData(): Call<DataResponse>
    }

    fun getApiInterface(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}