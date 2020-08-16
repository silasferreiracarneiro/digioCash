package br.com.silas.digiocash.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silas.digiocash.config.ResultApi
import br.com.silas.digiocash.model.HomeRequest
import br.com.silas.digiocash.repositoy.HomeRepository
import br.com.silas.digiocash.viewmodel.events.HomeViewModelEvent
import br.com.silas.digiocash.viewmodel.events.HomeViewModelState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    private val event = MutableLiveData<HomeViewModelEvent>()
    private val state = MutableLiveData<HomeViewModelState>()

    var viewEvent = event
    var viewState = state

    fun loadFiles() {
        event.postValue(HomeViewModelEvent.ShowLoading(View.VISIBLE))

        GlobalScope.launch {
            val result = repository.getFilesHome()
            afterCall(
                result
            )
        }
    }

    private fun afterCall(result: ResultApi<HomeRequest>) {
        when (result.isSucess()) {
            true -> state.postValue(HomeViewModelState.SucessCallApi(result.value))
            false -> state.postValue(HomeViewModelState.ErrorCallApi)
        }
        event.postValue(HomeViewModelEvent.ShowLoading(View.GONE))
    }
}