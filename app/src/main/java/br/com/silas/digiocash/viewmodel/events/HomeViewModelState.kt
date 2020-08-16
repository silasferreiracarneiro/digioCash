package br.com.silas.digiocash.viewmodel.events

import br.com.silas.digiocash.model.HomeRequest

sealed class HomeViewModelState {
    data class SucessCallApi(val result: HomeRequest?): HomeViewModelState()
    data class ErrorCallApi(val message: String?): HomeViewModelState()
}