package br.com.silas.digiocash.viewmodel.events

import br.com.silas.digiocash.api.response.HomeResponse

sealed class HomeViewModelState {
    data class SucessCallApi(val result: HomeResponse?): HomeViewModelState()
    data class ErrorCallApi(val message: String?): HomeViewModelState()
}