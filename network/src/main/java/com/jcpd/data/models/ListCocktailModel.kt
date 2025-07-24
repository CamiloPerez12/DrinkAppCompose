package com.jcpd.data.models

import com.google.gson.annotations.SerializedName

class ListCocktailModel (
   @SerializedName("drinks") val listDrink : List<CocktailModel>,
)