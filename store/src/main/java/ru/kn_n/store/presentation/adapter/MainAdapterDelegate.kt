package ru.kn_n.store.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.kn_n.core.databinding.ItemStatusMessageBinding
import ru.kn_n.core.utils.Status
import ru.kn_n.core.utils.gone
import ru.kn_n.core.utils.show
import ru.kn_n.store.databinding.GoodsGridBinding
import ru.kn_n.store.databinding.SectionTitleBinding
import ru.kn_n.store.databinding.SliderBannerBinding
import ru.kn_n.store.presentation.model.*

fun sectionTitleAdapterDelegate() = adapterDelegateViewBinding<SectionTitle, DisplayableItem, SectionTitleBinding>(
    { layoutInflater, root -> SectionTitleBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.title.text = item.title
    }
}

fun sliderBannerAdapterDelegate() = adapterDelegateViewBinding<SliderBanner, DisplayableItem, SliderBannerBinding>(
    { layoutInflater, root -> SliderBannerBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        val sliderBannerPageAdapter = SliderBannerPageAdapter()
        binding.viewPager.apply {
            adapter = sliderBannerPageAdapter
        }
        sliderBannerPageAdapter.setItems(item.pages)
    }
}

fun goodsGridAdapterDelegate(
    onProductClick: () -> Unit
) = adapterDelegateViewBinding<GoodsGrid, DisplayableItem, GoodsGridBinding>(
    { layoutInflater, root -> GoodsGridBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        val goodsAdapter = GoodsAdapter(onProductClick)
        binding.recyclerView.apply {
            adapter = goodsAdapter
        }
        goodsAdapter.setItems(item.goods)
    }
}

fun statusItemAdapterDelegate(
    onRefreshClick: () -> Unit
) = adapterDelegateViewBinding<Message, DisplayableItem, ItemStatusMessageBinding>(
    { layoutInflater, root -> ItemStatusMessageBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        when (item.status) {
            Status.EMPTY -> {
                binding.message.show()
                binding.refresh.gone()
                binding.loading.gone()
                binding.message.text = item.message
            }
            Status.ERROR -> {
                binding.message.show()
                binding.refresh.show()
                binding.loading.gone()
                binding.message.text = item.message
                binding.refresh.setOnClickListener {
                    onRefreshClick()
                }
            }
            Status.LOADING -> {
                binding.root.setBackgroundColor(context.getColor(ru.kn_n.core.R.color.gray_1))
                binding.message.setTextColor(context.getColor(ru.kn_n.core.R.color.dark_blue))
                binding.message.gone()
                binding.refresh.gone()
                binding.loading.show()
            }
        }
    }
}