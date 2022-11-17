package ru.kn_n.store.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.core.utils.loadImage
import ru.kn_n.store.databinding.GoodsBinding
import ru.kn_n.store.presentation.model.Goods

class GoodsAdapter(private val onProductClick: () -> Unit) : RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder>() {

    private val items = ArrayList<Goods>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val binding = GoodsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class GoodsViewHolder(
        private val binding: GoodsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Goods) {
            binding.price.text = data.price
            binding.price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.discountPrice.text = data.discountPrice
            binding.btnFavorite.isChecked = data.isFavorite
            binding.productName.text = data.productName
            binding.image.loadImage(data.imageUrl)

            binding.product.setOnClickListener {
                onProductClick()
            }
        }
    }

    fun setItems(data: List<Goods>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}