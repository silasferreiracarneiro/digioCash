package br.com.silas.digiocash.di.modules

import br.com.silas.digiocash.api.Api
import br.com.silas.digiocash.utils.Contantes.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi() = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(Api::class.java)
}