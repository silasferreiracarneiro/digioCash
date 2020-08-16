package br.com.silas.digiocash.api.response

import br.com.silas.digiocash.model.Cash
import br.com.silas.digiocash.model.Products
import br.com.silas.digiocash.model.Spotlight
import com.google.gson.annotations.SerializedName

data class HomeResponse (

    @SerializedName("spotlight") val spotlight : List<Spotlight>,
    @SerializedName("products") val products : List<Products>,
    @SerializedName("cash") val cash : Cash
)