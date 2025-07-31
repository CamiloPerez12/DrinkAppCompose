package com.jcpd.home.homeScreen

import com.jcpd.data.models.ListCocktailModel

data class HomeScreenState (
    val loading: Boolean = false,
    val error: String? = null,
    val data: ListCocktailModel? = null,
)