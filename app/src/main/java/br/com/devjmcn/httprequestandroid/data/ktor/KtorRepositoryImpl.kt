package br.com.devjmcn.httprequestandroid.data.ktor

import br.com.devjmcn.httprequestandroid.data.Repository
import br.com.devjmcn.httprequestandroid.data.model.CepModel
import br.com.devjmcn.httprequestandroid.util.Response
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorRepositoryImpl(private val ktorHttpClient: KtorHttpClient) : Repository {
    override suspend fun getCep(cep: String): Response<CepModel> {
        return try {
            val result = ktorHttpClient.ktorHttpClient.get("https://viacep.com.br/ws/${cep}/json/")
            Response.OnSuccess(result.body())
        } catch (e: Exception) {
            Response.OnFailure(e.message ?: "Error not found!")
        } finally {
            ktorHttpClient.ktorHttpClient.close()
        }
    }
}