package br.com.silas.digiocash.model

import com.google.gson.annotations.SerializedName

data class Products (

	@SerializedName("name") val name : String,
	@SerializedName("imageURL") val imageURL : String,
	@SerializedName("description") val description : String
)