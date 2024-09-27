package br.com.devjmcn.httprequestandroid.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CepModel(
    val logradouro:String,
    val bairro:String,
    val localidade:String,
    val uf:String,

)
