package ru.kn_n.product.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import ru.kn_n.core.utils.showOrHide
import ru.kn_n.product.R
import ru.kn_n.product.databinding.ColorWidgetBinding

@SuppressLint("Recycle", "CustomViewStyleable")
class ColorWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){
    private val binding = ColorWidgetBinding.inflate(LayoutInflater.from(context), this)
    private var isChecked = false

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ColorWidget)

            val color = typedArray.getString(R.styleable.ColorWidget_productColor)

            if (!color.isNullOrEmpty()) binding.color.setBackgroundColor(Color.parseColor(color))

            binding.checked.showOrHide(typedArray.getBoolean(R.styleable.ColorWidget_isChecked, false))
        }
    }

    fun setColor(color: String){
        binding.color.setBackgroundColor(Color.parseColor(color))
    }

    fun setChecked(){
        isChecked = true
        binding.checked.showOrHide(isChecked)
    }

    fun setUnchecked(){
        isChecked = false
        binding.checked.showOrHide(isChecked)
    }

    fun isChecked(): Boolean = isChecked
}