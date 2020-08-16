package br.com.silas.digiocash.api

import br.com.silas.digiocash.api.response.HomeResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {

    @GET("/sandbox/products")
    fun getHome(): Deferred<HomeResponse>
}