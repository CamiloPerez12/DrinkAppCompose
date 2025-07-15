package com.jcpd.drinkapp.data


import com.jcpd.drinkapp.data.models.ListCocktailModel
import com.jcpd.drinkapp.data.network.NetworkDataSource
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : Repository {
    override suspend fun getRandomCocktail(): Response<ListCocktailModel> {
        return networkDataSource.getRandomCocktail()
    }
}