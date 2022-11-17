package ru.kn_n.cart.data.repository

import ru.kn_n.cart.data.api.CartAPI
import ru.kn_n.cart.data.mapper.CartResponseMapper
import ru.kn_n.core.data.Repository
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val api: CartAPI,
    private val mapper: CartResponseMapper
) : Repository {
    suspend fun getCart() = mapper.mapCartResponse(api.getCartInfo())
}