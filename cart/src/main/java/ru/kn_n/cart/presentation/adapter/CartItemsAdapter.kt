package ru.kn_n.cart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.cart.databinding.ItemCartRecyclerViewBinding
import ru.kn_n.cart.domain.model.BasketEntity
import ru.kn_n.core.utils.loadImage

class CartItemsAdapter : RecyclerView.Adapter<CartItemsAdapter.CartItemsViewHolder>() {

    private val items = ArrayList<BasketEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemsAdapter.CartItemsViewHolder {
        val binding = ItemCartRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemsAdapter.CartItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CartItemsViewHolder(
        private val binding: ItemCartRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BasketEntity) {
            binding.image.loadImage(data.productImage)
            binding.productName.text = data.productName
            binding.price.text = data.price
            binding.count.text = ONE
        }
    }

    fun setItems(data: List<BasketEntity>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    companion object{
        private const val ONE = "1"
    }
}