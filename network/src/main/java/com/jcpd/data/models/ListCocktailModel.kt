package com.jcpd.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class ListCocktailModel (
   @SerializedName("drinks") val listDrink : List<CocktailModel>,
)