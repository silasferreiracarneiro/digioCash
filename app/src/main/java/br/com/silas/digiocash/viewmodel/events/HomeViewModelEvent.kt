package br.com.silas.digiocash.viewmodel.events

sealed class HomeViewModelEvent {
    data class ShowLoading(val status: Int): HomeViewModelEvent()
}