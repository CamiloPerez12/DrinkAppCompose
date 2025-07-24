package com.jcpd.data


import com.jcpd.data.models.ListCocktailModel
import retrofit2.Response

interface Repository {

    suspend fun getRandomCocktail(): Response<ListCocktailModel>

}