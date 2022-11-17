package ru.kn_n.store.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.store.databinding.ItemCategoryBinding
import ru.kn_n.store.presentation.model.Category

class CategoriesAdapter(private val onCategoryClick: (category: String) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val items = ArrayList<Category>()
    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(items[position], position, selectedItem)
    }

    override fun getItemCount(): Int = items.size

    inner class CategoriesViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: Category,
            position: Int,
            selectedPosition: Int
        ) {
            binding.category.setLabel(data.categoryName)
            binding.category.setIcon(data.categoryImage)

            when {
                selectedPosition == -1 && position == 0 -> binding.category.setChecked()
                selectedPosition == position -> binding.category.setChecked()
                else -> binding.category.setUnchecked()
            }

            binding.category.setOnClickListener {
                selectedItem = position
                onCategoryClick(data.categoryName)
                notifyDataSetChanged()
            }
        }

    }

    fun setItems(data: List<Category>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}