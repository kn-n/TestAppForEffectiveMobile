package ru.kn_n.cart.data.repository

import ru.kn_n.cart.data.api.CartAPI
import ru.kn_n.cart.data.mapper.CartResponseMapper
import ru.kn_n.cart.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartAPI,
    private val mapper: CartResponseMapper
) : CartRepository {
    override suspend fun getCart() = mapper.mapCartResponse(api.getCartInfo())
}