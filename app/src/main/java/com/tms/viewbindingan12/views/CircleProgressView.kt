package com.tms.viewbindingan12.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.tms.viewbindingan12.R

class CircleProgressView(context: Context, attr: AttributeSet) : View(context, attr) {

    companion object {
        private const val START_ANGLE = 135f
        private const val STROKE_WIDTH = 40f
        private const val MARGIN_COEFFICIENT = 0.8f
        private const val MAX_ANGLE = 270f
        private const val TIME_ANIMATION = 4000L
        private const val MAX_PROGRESS_VALUE = 100f
    }

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            color = context.getColor(R.color.teal_200)
            style = Paint.Style.STROKE
            strokeWidth = STROKE_WIDTH
        }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            color = context.getColor(R.color.purple_200)
            style = Paint.Style.STROKE
            strokeWidth = STROKE_WIDTH
        }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            color = context.getColor(R.color.black)
        }


    private var radius = 0f
    private var centerX = 0f
    private var centerY = 0f
    private val oval: RectF by lazy {
        RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }
    private val progressAnimator: ValueAnimator
        get() = ValueAnimator.ofFloat(sweepAngle, progressValue)
            .apply {
                duration = TIME_ANIMATION
                addUpdateListener {
                    sweepAngle = it.animatedValue as Float
                    invalidate()
                }
            }

    private var sweepAngle = 0f
    private var progressValue = 0f

    private val currentProgressInPersents: String
        get() = sweepAngle.toPercents().toInt().toString()

    fun startAnimation(progress: Float) {
        progressValue = progress.toAngle()
        progressAnimator.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        centerX = width / 2f
        centerY = height / 2f
        radius = width / 2f * MARGIN_COEFFICIENT
        textPaint.textSize = width * 0.4f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(oval, START_ANGLE, MAX_ANGLE, true, backgroundPaint)
        canvas.drawArc(oval, START_ANGLE, sweepAngle, true, progressPaint)
        canvas.drawText(
            currentProgressInPersents,
            centerX - textPaint.measureText(currentProgressInPersents) / 2f,
            centerY + width * 0.1f,
            textPaint
        )
    }

    private fun Float.toAngle() = this * MAX_ANGLE / MAX_PROGRESS_VALUE
    private fun Float.toPercents() = this * MAX_PROGRESS_VALUE / MAX_ANGLE
}