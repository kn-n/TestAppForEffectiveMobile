package ru.kn_n.cart.domain.repository

import ru.kn_n.cart.domain.model.CartEntity

interface CartRepository {
    suspend fun getCart(): CartEntity
}