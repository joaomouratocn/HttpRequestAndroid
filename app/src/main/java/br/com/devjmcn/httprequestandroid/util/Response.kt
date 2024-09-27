package br.com.devjmcn.httprequestandroid.util

sealed class Response<out T> {
    data class OnSuccess<T>(val data:T):Response<T>()
    data class OnFailure(val message:String):Response<Nothing>()
}