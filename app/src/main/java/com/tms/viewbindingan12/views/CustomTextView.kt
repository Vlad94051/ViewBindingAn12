package com.tms.viewbindingan12.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.tms.viewbindingan12.R

class CustomTextView(
    context: Context,
    attrs: AttributeSet
) : AppCompatTextView(context, attrs) {

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomTextView, 0, 0
        ).apply {
            setTextReversed(getBoolean(R.styleable.CustomTextView_isReversed, false))
            setTextReflected(getBoolean(R.styleable.CustomTextView_isReflected, false))
        }
    }

    var isReflected: Boolean = false
        set(value) {
            setTextReflected(value)
            field = value
        }

    var isReversed: Boolean = false
        set(value) {
            setTextReversed(value)
            field = value
        }


    private fun setTextReversed(isReversed: Boolean) {
        if (isReversed) {
            val reversedText = text.reversed()
            text = reversedText
        }
    }

    private fun setTextReflected(isReflected: Boolean) {
        if (isReflected) {
            rotationY = 180f
        }
    }
}