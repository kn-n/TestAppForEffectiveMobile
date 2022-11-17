package ru.kn_n.cart.domain.model

import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO

data class CartEntity(
    val basket: List<BasketEntity>,
    val delivery: String = String.EMPTY,
    val id: String = String.EMPTY,
    val totalPrice: String = String.EMPTY
)

data class BasketEntity(
    val id: Int = Int.ZERO,
    val productImage: String = String.EMPTY,
    val price: String = String.EMPTY,
    val productName: String = String.EMPTY
)