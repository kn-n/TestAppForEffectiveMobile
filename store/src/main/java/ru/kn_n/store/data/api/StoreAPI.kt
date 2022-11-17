package ru.kn_n.store.data.api

import retrofit2.http.GET
import ru.kn_n.store.data.model.PhonesProductsResponse

interface StoreAPI {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getPhonesProducts(): PhonesProductsResponse
}