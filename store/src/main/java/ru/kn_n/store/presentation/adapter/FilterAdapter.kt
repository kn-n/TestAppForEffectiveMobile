package ru.kn_n.store.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ru.kn_n.store.R
import ru.kn_n.store.databinding.ItemFilterBinding

class FilterAdapter(context: Context, items: List<String>) : ArrayAdapter<String>(context, 0, items) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(context), parent, false)

        getItem(position)?.let { item ->
            setItem(binding, item)
        }

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(context), parent, false)

        if (position == 0) {
            binding.text.text = context.getString(R.string.select)
            binding.text.setTextColor(context.getColor(ru.kn_n.core.R.color.gray_5))
            binding.text.setOnClickListener {
                val root = parent.rootView
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
            }
        } else {
            getItem(position)?.let { item ->
                setItem(binding, item)
            }
        }

        return binding.root
    }

    override fun getItem(position: Int): String? {
        if (position == 0) {
            return super.getItem(position)
        }
        return super.getItem(position - 1)
    }

    override fun getCount() = super.getCount() + 1

    override fun isEnabled(position: Int) = position != 0

    private fun setItem(binding: ItemFilterBinding, item: String) {
        binding.text.text = item
    }
}