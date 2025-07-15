package com.jcpd.drinkapp.data.models

import com.google.gson.annotations.SerializedName

class ListCocktailModel (
   @SerializedName("drinks") val listDrink : List<CocktailModel>,
)