package ru.kn_n.product.data.model

data class ProductResponse(
    val CPU: String?,
    val camera: String?,
    val capacity: List<String>?,
    val color: List<String>?,
    val id: String?,
    val images: List<String>?,
    val isFavorites: Boolean?,
    val price: Int?,
    val rating: Double?,
    val sd: String?,
    val ssd: String?,
    val title: String?
)