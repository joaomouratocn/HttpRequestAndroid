package br.com.devjmcn.httprequestandroid.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CepModel(
    val logradouro:String,
    val complemento:String,
    val bairro:String,
    @SerialName("localidade")
    val cidade:String,
    @SerialName("uf")
    val estado:String,

)
