package ru.kn_n.product.data.mapper

import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO
import ru.kn_n.core.utils.toStringInMoneyFormat
import ru.kn_n.product.data.model.ProductResponse
import ru.kn_n.product.domain.model.ProductEntity
import ru.kn_n.product.domain.model.ShopEntity
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {
    fun mapProductResponse(response: ProductResponse): ProductEntity {
        return ProductEntity(
            id = response.id ?: String.EMPTY,
            images = response.images ?: emptyList(),
            isFavorite = response.isFavorites ?: false,
            price = if (response.price != null) response.price.toStringInMoneyFormat else String.EMPTY,
            rating = if (response.rating != null) response.rating.toFloat() else Float.ZERO,
            productName = response.title ?: String.EMPTY,
            shopInfo = mapToShopEntity(response)
        )
    }

    private fun mapToShopEntity(response: ProductResponse): ShopEntity{
        return ShopEntity(
            cpu = response.CPU ?: String.EMPTY,
            camera = response.camera ?: String.EMPTY,
            capacity = if (!response.capacity.isNullOrEmpty()) response.capacity.map { "$it Gb" } else emptyList(),
            color = response.color ?: emptyList(),
            sd = response.sd ?: String.EMPTY,
            ssd = response.ssd ?: String.EMPTY,
        )
    }
}