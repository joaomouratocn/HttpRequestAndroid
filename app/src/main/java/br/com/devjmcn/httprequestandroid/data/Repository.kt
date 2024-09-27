package br.com.devjmcn.httprequestandroid.data

import br.com.devjmcn.httprequestandroid.data.model.CepModel
import br.com.devjmcn.httprequestandroid.util.Response

interface Repository {
    suspend fun getCep(cep:String):Response<CepModel>
}