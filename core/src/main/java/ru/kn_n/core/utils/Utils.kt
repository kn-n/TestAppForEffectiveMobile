package ru.kn_n.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

val String.Companion.EMPTY: String
    get() = ""

val Float.Companion.ZERO: Float
    get() = 0.0f

val Int.Companion.ZERO: Int
    get() = 0

val Int.toStringInMoneyFormat: String
    get() = "$${"%,d".format(this)}"

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showOrHide(isVisible: Boolean) {
    if (isVisible) this.show()
    else this.hide()
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}