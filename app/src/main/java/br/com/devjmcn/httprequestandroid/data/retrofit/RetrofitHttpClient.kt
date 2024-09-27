package br.com.devjmcn.httprequestandroid.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttpClient {
    private const val BASE_URL = "https://viacep.com.br/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}