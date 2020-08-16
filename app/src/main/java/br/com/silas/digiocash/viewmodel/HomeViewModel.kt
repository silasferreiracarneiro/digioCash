package br.com.silas.digiocash.viewmodel

import androidx.lifecycle.ViewModel
import br.com.silas.digiocash.repositoy.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {


}