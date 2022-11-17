package ru.kn_n.store.data.model

data class PhonesProductsResponse(
    val home_store: List<HomeStoreResponse>?,
    val best_seller: List<BestSellerResponse>?
)

data class HomeStoreResponse(
    val id: Int?,
    val is_new: Boolean?,
    val title: String?,
    val subtitle: String?,
    val picture: String?,
    val is_buy: Boolean?
)

data class BestSellerResponse(
    val id: Int?,
    val is_favorites: Boolean?,
    val title: String?,
    val price_without_discount: Int?,
    val discount_price: Int?,
    val picture: String?
)
