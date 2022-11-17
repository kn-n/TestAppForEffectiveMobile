package ru.kn_n.store.domain.model

import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO

data class PhonesProductsEntity (
    val hotSales: List<HotSaleProductEntity>,
    val bestSeller: List<BestSellerProductEntity>
)

data class HotSaleProductEntity(
    val id: Int = Int.ZERO,
    val isNew: Boolean = false,
    val productName: String = String.EMPTY,
    val productDescription: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
    val isBuy: Boolean = false
)

data class BestSellerProductEntity(
    val id: Int = Int.ZERO,
    val isFavorite: Boolean = false,
    val productName: String = String.EMPTY,
    val price: Int = Int.ZERO,
    val discountPrice: Int = Int.ZERO,
    val imageUrl: String = String.EMPTY
)