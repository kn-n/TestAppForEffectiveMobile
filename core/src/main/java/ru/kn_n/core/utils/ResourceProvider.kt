package ru.kn_n.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    private val context: Context
) {
    fun getString(resId: Int): String = context.getString(resId)
}