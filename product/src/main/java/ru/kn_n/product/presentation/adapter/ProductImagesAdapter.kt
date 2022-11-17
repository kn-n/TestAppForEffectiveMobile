package ru.kn_n.product.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.core.utils.loadImage
import ru.kn_n.product.databinding.ItemProductViewPagerBinding

class ProductImagesAdapter : RecyclerView.Adapter<ProductImagesAdapter.ProductImagesViewHolder>() {

    private val items = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesAdapter.ProductImagesViewHolder {
        val binding = ItemProductViewPagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductImagesAdapter.ProductImagesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ProductImagesViewHolder(
        private val binding: ItemProductViewPagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String){
            binding.image.loadImage(data)
        }
    }

    fun setItems(data: List<String>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}