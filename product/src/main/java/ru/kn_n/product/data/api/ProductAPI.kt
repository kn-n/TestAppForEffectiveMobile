package ru.kn_n.product.data.api

import retrofit2.http.GET
import ru.kn_n.product.data.model.ProductResponse

interface ProductAPI {
    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductInfo(): ProductResponse
}