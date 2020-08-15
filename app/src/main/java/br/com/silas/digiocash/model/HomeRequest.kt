package br.com.silas.digiocash.model

import com.google.gson.annotations.SerializedName

data class HomeRequest (

	@SerializedName("spotlight") val spotlight : List<Spotlight>,
	@SerializedName("products") val products : List<Products>,
	@SerializedName("cash") val cash : Cash
)