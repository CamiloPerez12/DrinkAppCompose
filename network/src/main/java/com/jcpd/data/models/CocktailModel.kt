package com.jcpd.data.models

import com.google.gson.annotations.SerializedName

class CocktailModel (
    @SerializedName("idDrink") val idDrink : String,
    @SerializedName("strDrink") val strDrink : String,
    @SerializedName("strCategory") val strCategory : String,
    @SerializedName("strAlcoholic") val strAlcoholic : String,
    @SerializedName("strGlass") val strGlass : String,
    @SerializedName("strInstructions") val strInstructions : String,
    @SerializedName("strInstructionsES") val strInstructionsES : String,
    @SerializedName("strDrinkThumb") val strDrinkThumb : String,
    @SerializedName("strIngredient1") val strIngredient1 : String,
    @SerializedName("strIngredient2") val strIngredient2 : String,
    @SerializedName("strIngredient3") val strIngredient3 : String,
    @SerializedName("strIngredient4") val strIngredient4 : String,
    @SerializedName("strIngredient5") val strIngredient5 : String,
    @SerializedName("strIngredient6") val strIngredient6 : String,
    @SerializedName("strIngredient7") val strIngredient7 : String,
    @SerializedName("strIngredient8") val strIngredient8 : String,
    @SerializedName("strMeasure1") val strMeasure1 : String,
    @SerializedName("strMeasure2") val strMeasure2 : String,
    @SerializedName("strMeasure3") val strMeasure3 : String,
    @SerializedName("strMeasure4") val strMeasure4 : String,
    @SerializedName("strMeasure5") val strMeasure5 : String,
    @SerializedName("strMeasure6") val strMeasure6 : String,
    @SerializedName("strMeasure7") val strMeasure7 : String,
    @SerializedName("strMeasure8") val strMeasure8 : String,

)