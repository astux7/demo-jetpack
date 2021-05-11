package com.example.demo.ui.toolkit

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DimenRes
import com.example.demo.R

class ShineDivider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val SHINE_HEIGHT = dpToPixels(2)

    var isLight: Boolean = false
        set(value) {
            field = value
            setShineDividerBackground()
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShineDivider)
        isLight = typedArray.getBoolean(R.styleable.ShineDivider_isLight, false)
        typedArray.recycle()
        setShineDividerBackground()
    }

    private fun setShineDividerBackground() {
        if (isLight) setBackgroundResource(R.drawable.shine_divider) else setBackgroundResource(R.drawable.grey_shine_divider)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, SHINE_HEIGHT)
    }

    class Builder : ComponentBuilder<ShineDivider, ShineDividerData>() {

        override fun build(context: Context): ShineDivider = ShineDivider(context)

        override fun populateView(context: Context, view: ShineDivider) {
            with(view) {
                data?.let {
                    this.isLight = it.isLight
                }
            }
        }
    }


}