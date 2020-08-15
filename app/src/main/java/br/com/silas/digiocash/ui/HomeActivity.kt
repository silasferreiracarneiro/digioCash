package br.com.silas.digiocash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.silas.digiocash.R
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}