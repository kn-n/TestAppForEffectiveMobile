package ru.kn_n.store.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.kn_n.store.presentation.model.DisplayableItem

class MainStoreAdapter(
    onProductClick: () -> Unit,
    onRefreshClick: () -> Unit
) : ListDelegationAdapter<List<DisplayableItem>> (
    sectionTitleAdapterDelegate(),
    sliderBannerAdapterDelegate(),
    goodsGridAdapterDelegate(onProductClick),
    statusItemAdapterDelegate(onRefreshClick)
)