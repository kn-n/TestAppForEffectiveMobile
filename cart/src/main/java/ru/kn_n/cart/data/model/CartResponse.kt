package ru.kn_n.cart.data.model

data class CartResponse(
    val basket: List<BasketResponse>?,
    val delivery: String?,
    val id: String?,
    val total: Int?
)

data class BasketResponse(
    val id: Int?,
    val images: String?,
    val price: Int?,
    val title: String?
)