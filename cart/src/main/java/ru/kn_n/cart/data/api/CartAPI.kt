package ru.kn_n.cart.data.api

import retrofit2.http.GET
import ru.kn_n.cart.data.model.CartResponse

interface CartAPI {
    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCartInfo(): CartResponse
}