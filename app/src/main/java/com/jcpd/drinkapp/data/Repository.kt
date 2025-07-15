package com.jcpd.drinkapp.data


import com.jcpd.drinkapp.data.models.ListCocktailModel
import retrofit2.Response

interface Repository {

    suspend fun getRandomCocktail(): Response<ListCocktailModel>

}