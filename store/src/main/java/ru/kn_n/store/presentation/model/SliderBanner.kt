package ru.kn_n.store.presentation.model

data class SliderBanner(
    val pages: List<SliderBannerPage>
): DisplayableItem

data class SliderBannerPage(
    val productName: String,
    val productDescription: String,
    val imageUrl: String,
    val isNew: Boolean,
    val isBuy: Boolean
): DisplayableItem