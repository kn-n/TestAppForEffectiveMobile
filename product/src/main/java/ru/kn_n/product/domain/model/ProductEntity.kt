package ru.kn_n.product.domain.model

import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO
import java.io.Serializable

data class ProductEntity(
    val id: String = String.EMPTY,
    val images: List<String> = emptyList(),
    val isFavorite: Boolean = false,
    val price: String = String.EMPTY,
    val rating: Float = Float.ZERO,
    val productName: String = String.EMPTY,
    val shopInfo: ShopEntity
)

data class ShopEntity(
    val cpu: String = String.EMPTY,
    val camera: String = String.EMPTY,
    val capacity: List<String> = emptyList(),
    val color: List<String> = emptyList(),
    val sd: String = String.EMPTY,
    val ssd: String = String.EMPTY,
) : Serializable
