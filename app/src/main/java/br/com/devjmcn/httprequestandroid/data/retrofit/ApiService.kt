package br.com.devjmcn.httprequestandroid.data.retrofit

import br.com.devjmcn.httprequestandroid.data.model.CepModel
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("ws/{cep}/json/")
    suspend fun getCep(@Path("cep") cep: String): CepModel
}