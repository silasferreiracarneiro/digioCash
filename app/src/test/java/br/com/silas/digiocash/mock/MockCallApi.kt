package br.com.silas.digiocash.mock

import br.com.silas.digiocash.api.response.HomeResponse
import br.com.silas.digiocash.config.ResultApi
import br.com.silas.digiocash.config.read
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

fun getSucessCallViewModel(): ResultApi<HomeResponse> =
    ResultApi.createSucess(read("request_sucess.json"))

fun newHomeResponseViewModel(): HomeResponse = read("request_sucess.json")

fun getErrorCallViewModel(): ResultApi<HomeResponse> =
    ResultApi.createError(Throwable("Erro ao fazer a chamada"))

fun getSucessCallApiRepository(): Deferred<HomeResponse> {
    val userAccount = read<HomeResponse>("request_sucess.json")
    return CompletableDeferred(userAccount)
}

fun getErrorCallApiRepository() = CompletableDeferred<HomeResponse>()