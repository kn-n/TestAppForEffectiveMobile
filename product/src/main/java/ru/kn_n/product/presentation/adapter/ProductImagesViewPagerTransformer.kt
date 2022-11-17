package ru.kn_n.product.presentation.adapter

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import java.lang.Math.abs

class ProductImagesViewPagerTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {

        val nextItemVisiblePx = 70
        val currentItemHorizontalMarginPx = 70
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        page.translationX = -pageTranslationX * position
        page.scaleY = 1 - (0.25f * abs(position))
    }
}
