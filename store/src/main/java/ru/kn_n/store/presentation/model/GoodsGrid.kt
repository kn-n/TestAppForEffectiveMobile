package ru.kn_n.store.presentation.model

data class GoodsGrid(
    val goods: List<Goods>
): DisplayableItem

data class Goods(
    val isFavorite: Boolean,
    val imageUrl: String,
    val price: String,
    val discountPrice: String,
    val productName: String
): DisplayableItem
