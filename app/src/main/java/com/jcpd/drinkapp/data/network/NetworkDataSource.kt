package com.jcpd.drinkapp.data.network

import com.jcpd.drinkapp.data.models.ListCocktailModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkDataSource {

    @GET("random.php")
    suspend fun getRandomCocktail(): Response<ListCocktailModel>

}