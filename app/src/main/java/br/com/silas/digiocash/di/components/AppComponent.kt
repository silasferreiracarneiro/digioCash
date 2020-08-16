package br.com.silas.digiocash.di.components

import br.com.silas.digiocash.di.modules.ContextModule
import br.com.silas.digiocash.di.modules.NetworkModule
import br.com.silas.digiocash.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ContextModule::class])
interface AppComponent {
    fun inject(activity: HomeActivity)
}