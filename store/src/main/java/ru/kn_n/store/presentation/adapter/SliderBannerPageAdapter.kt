package ru.kn_n.store.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.core.utils.loadImage
import ru.kn_n.core.utils.showOrHide
import ru.kn_n.store.databinding.SliderBannerPageBinding
import ru.kn_n.store.presentation.model.SliderBannerPage

class SliderBannerPageAdapter : RecyclerView.Adapter<SliderBannerPageAdapter.SliderBannerPageViewHolder>() {

    private val items = ArrayList<SliderBannerPage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderBannerPageViewHolder {
        val binding = SliderBannerPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SliderBannerPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderBannerPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class SliderBannerPageViewHolder(
        private val binding: SliderBannerPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SliderBannerPage) {
            binding.productName.text = data.productName
            binding.productDescription.text = data.productDescription
            binding.markerNew.showOrHide(data.isNew)
            binding.buttonBuy.showOrHide(data.isBuy)
            binding.image.loadImage(data.imageUrl)
        }
    }

    fun setItems(data: List<SliderBannerPage>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}