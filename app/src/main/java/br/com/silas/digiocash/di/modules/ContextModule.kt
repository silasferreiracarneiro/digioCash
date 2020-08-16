package br.com.silas.digiocash.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule(val context: Context) {

    @Binds
    abstract fun bindContext(application: Application): Context
}