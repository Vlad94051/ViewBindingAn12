package com.tms.viewbindingan12.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.tms.viewbindingan12.R

class CircleView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var radius: Float = 0f
    private var circleColor: Int = 0
        set(value) {
            circlePaint.color = value
            field = value
        }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0)
            .apply {
                radius = getDimension(R.styleable.CircleView_circleRadius, 0f)
                circleColor =
                    getColor(R.styleable.CircleView_color, context.getColor(R.color.teal_200))
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(radius.toInt() * 2, radius.toInt() * 2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(height / 2f, width / 2f, radius, circlePaint)
    }
}