package br.com.silas.digiocash

import android.app.Application;
import br.com.silas.digiocash.di.components.AppComponent
import br.com.silas.digiocash.di.components.DaggerAppComponent

class BaseApplication: Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}