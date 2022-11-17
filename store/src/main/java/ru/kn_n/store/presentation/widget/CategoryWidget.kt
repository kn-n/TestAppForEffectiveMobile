package ru.kn_n.store.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.kn_n.store.R
import ru.kn_n.store.databinding.CategoryWidgetBinding

@SuppressLint("Recycle", "CustomViewStyleable")
class CategoryWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = CategoryWidgetBinding.inflate(LayoutInflater.from(context), this)
    private var isChecked = false

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.categoryWidget)

            binding.label.text = typedArray.getText(R.styleable.categoryWidget_label)
            binding.icon.setImageDrawable(typedArray.getDrawable(R.styleable.categoryWidget_icon))
            isChecked = typedArray.getBoolean(R.styleable.categoryWidget_checked, false)

            typedArray.recycle()
        }
    }

    fun setLabel(text: String) {
        binding.label.text = text
    }

    fun setIcon(resId: Int) {
        binding.icon.setImageResource(resId)
    }

    fun setChecked() {
        isChecked = true
        binding.label.setTextColor(context.getColor(ru.kn_n.core.R.color.orange))
        binding.icon.setColorFilter(context.getColor(ru.kn_n.core.R.color.white))
        binding.background.setBackgroundResource(ru.kn_n.core.R.drawable.circle_orange)
    }

    fun setUnchecked() {
        isChecked = false
        binding.label.setTextColor(context.getColor(ru.kn_n.core.R.color.dark_blue))
        binding.icon.setColorFilter(context.getColor(ru.kn_n.core.R.color.gray_3))
        binding.background.setBackgroundResource(ru.kn_n.core.R.drawable.circle_white)
    }

    fun isChecked(): Boolean = isChecked
}