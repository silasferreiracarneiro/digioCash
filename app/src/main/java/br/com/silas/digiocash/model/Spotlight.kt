package br.com.silas.digiocash.model

import com.google.gson.annotations.SerializedName

data class Spotlight (

	@SerializedName("name") val name : String,
	@SerializedName("bannerURL") val bannerURL : String,
	@SerializedName("description") val description : String
)