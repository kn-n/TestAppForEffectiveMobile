package ru.kn_n.product.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.product.databinding.ItemMemoryRecyclerViewBinding

class CapacitiesAdapter : RecyclerView.Adapter<CapacitiesAdapter.CapacitiesViewHolder>() {

    private val items = ArrayList<String>()
    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapacitiesAdapter.CapacitiesViewHolder {
        val binding = ItemMemoryRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CapacitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CapacitiesAdapter.CapacitiesViewHolder, position: Int) {
        holder.bind(items[position], position, selectedItem)
    }

    override fun getItemCount(): Int = items.size

    inner class CapacitiesViewHolder(
        private val binding: ItemMemoryRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: String,
            position: Int,
            selectedPosition: Int
        ) {
            binding.chip.text = data

            when {
                selectedPosition == -1 && position == 0 -> binding.chip.isChecked = true
                selectedPosition == position -> binding.chip.isChecked = true
                else -> binding.chip.isChecked = false
            }

            binding.chip.setOnClickListener {
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