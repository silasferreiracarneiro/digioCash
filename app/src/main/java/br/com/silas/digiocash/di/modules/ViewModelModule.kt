package br.com.silas.digiocash.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silas.digiocash.di.ViewModelKey
import br.com.silas.digiocash.viewmodel.HomeViewModel
import br.com.silas.digiocash.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}