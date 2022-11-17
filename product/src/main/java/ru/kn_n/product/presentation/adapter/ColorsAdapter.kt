package ru.kn_n.product.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.product.databinding.ItemColorsRecyclerViewBinding

class ColorsAdapter : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {

    private val items = ArrayList<String>()
    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsAdapter.ColorsViewHolder {
        val binding = ItemColorsRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ColorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorsAdapter.ColorsViewHolder, position: Int) {
        holder.bind(items[position], position, selectedItem)
    }

    override fun getItemCount(): Int = items.size

    inner class ColorsViewHolder(
        private val binding: ItemColorsRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: String,
            position: Int,
            selectedPosition: Int
        ) {
            binding.colorCheck.setColor(data)

            when {
                selectedPosition == -1 && position == 0 -> binding.colorCheck.setChecked()
                selectedPosition == position -> binding.colorCheck.setChecked()
                else -> binding.colorCheck.setUnchecked()
            }

            binding.colorCheck.setOnClickListener {
                selectedItem = position
                notifyDataSetChanged()
            }
        }
    }

    fun setItems(data: List<String>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}