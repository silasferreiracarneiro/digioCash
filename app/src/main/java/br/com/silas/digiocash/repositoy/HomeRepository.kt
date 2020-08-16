package br.com.silas.digiocash.repositoy

import br.com.silas.digiocash.api.Api
import br.com.silas.digiocash.config.doResquest
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: Api) {

    suspend fun getFilesHome() =
        doResquest {
            api.getHome().await()
        }
}