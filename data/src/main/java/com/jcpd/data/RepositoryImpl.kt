package com.jcpd.data


import com.jcpd.data.models.ListCocktailModel
import com.jcpd.network.NetworkDataSource
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : Repository {
    override suspend fun getRandomCocktail(): Response<ListCocktailModel> {
        return networkDataSource.getRandomCocktail()
    }
}