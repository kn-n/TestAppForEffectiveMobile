package ru.kn_n.store.data.mapper

import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO
import ru.kn_n.store.data.model.BestSellerResponse
import ru.kn_n.store.data.model.PhonesProductsResponse
import ru.kn_n.store.domain.model.BestSellerProductEntity
import ru.kn_n.store.domain.model.PhonesProductsEntity
import javax.inject.Inject

class FilterMapper @Inject constructor() {
    fun mapListBestSellerResponse(
        response: PhonesProductsResponse,
        brand: String,
        price: String
    ): PhonesProductsEntity {
        return PhonesProductsEntity(
            hotSales = emptyList(),
            bestSeller =
            if (!response.best_seller.isNullOrEmpty())
                mapListBestSellerResponse(response.best_seller, brand, price)
            else emptyList()
        )
    }

    private fun mapListBestSellerResponse(
        response: List<BestSellerResponse>,
        brand: String,
        price: String
    ): List<BestSellerProductEntity> {
        var list = response.map { mapBestSellerResponse(it) }
        if (brand.isNotEmpty()) {
            list = list.filter { it.productName.contains(brand) }
        }
        return list
    }

    private fun mapBestSellerResponse(
        response: BestSellerResponse
    ): BestSellerProductEntity {
        return BestSellerProductEntity(
            id = response.id ?: Int.ZERO,
            isFavorite = response.is_favorites ?: false,
            productName = response.title ?: String.EMPTY,
            price = response.discount_price ?: Int.ZERO,
            discountPrice = response.price_without_discount ?: Int.ZERO,
            imageUrl = response.picture ?: String.EMPTY
        )
    }
}