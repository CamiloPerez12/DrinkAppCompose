package com.jcpd.network

import com.jcpd.data.models.ListCocktailModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkDataSource {

    @GET("random.php")
    suspend fun getRandomCocktail(): Response<ListCocktailModel>

}