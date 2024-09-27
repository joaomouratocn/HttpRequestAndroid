package br.com.devjmcn.httprequestandroid.data.retrofit

import br.com.devjmcn.httprequestandroid.data.Repository
import br.com.devjmcn.httprequestandroid.data.model.CepModel
import br.com.devjmcn.httprequestandroid.util.Response

class RetrofitRepositoryImpl(private val retrofitHttpClient: RetrofitHttpClient) : Repository{
    override suspend fun getCep(cep: String): Response<CepModel> {
        return try {
            val response = retrofitHttpClient.apiService.getCep(cep)
            if (response.localidade.isBlank()) {
                Response.OnFailure("Invalid data or CEP not found!")
            }
            Response.OnSuccess(response)
        }catch (e:Exception){
            Response.OnFailure(e.message ?: "Error not found!")
        }finally {
            retrofitHttpClient.apiService
        }
    }
}