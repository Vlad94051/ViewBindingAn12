package com.tms.viewbindingan12.views

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.*
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.tms.viewbindingan12.R

class ProgressView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    companion object {
        private const val PROGRESS_LINE_WIDTH = 20f
        private const val MAX_PROGRESS_VALUE = 100
        private const val MIN_ANGLE = 135f
        private const val MAX_ANGLE = 270f
        private const val ANIMATION_DURATION = 4000L

        private const val MARGIN_COEFFICIENT = 0.8f
        private const val TEXT_SIZE_COEFFICIENT = 0.4f
        private const val TEXT_Y_COEFFICIENT = 0.1f
    }

    private val textPaint = Paint(ANTI_ALIAS_FLAG)
        .apply {
            color = context.getColor(R.color.black)
        }

    private val backgroundPaint = Paint(ANTI_ALIAS_FLAG)
        .apply {
            strokeWidth = PROGRESS_LINE_WIDTH
            style = Style.STROKE
            color = context.getColor(R.color.teal_200)
        }

    private val progressPaint = Paint(ANTI_ALIAS_FLAG)
        .apply {
            strokeWidth = PROGRESS_LINE_WIDTH
            style = Style.STROKE
            color = context.getColor(R.color.purple_500)
        }

    private val centerOfX: Float get() = (width / 2).toFloat()
    private val centerOfY: Float get() = (height / 2).toFloat()
    private val radius: Float get() = (height / 2f) * MARGIN_COEFFICIENT

    private val progressValueAnimator
        get() = ValueAnimator.ofFloat(currentProgress, toProgress)
            .apply {
                duration = ANIMATION_DURATION

                addUpdateListener { animator ->
                    sweepAngle = (animator.animatedValue as Float)
                    invalidate()
                }

                addOnEndListener {
                    currentProgress = toProgress }
            }

    private var sweepAngle: Float = 0f
    private var currentProgress: Float = 0f
    private var toProgress: Float = MAX_ANGLE
    private val currentProgressInPresents
        get() = sweepAngle.toPercents().toInt().toString()

    private val arcOval by lazy {
        RectF(
            centerOfX - radius,
            centerOfY - radius,
            centerOfX + radius,
            centerOfY + radius
        )
    }

    var progress: Float = 0f
        set(value) {
            field = value
            animateTo(value)
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        textPaint.textSize = width * TEXT_SIZE_COEFFICIENT
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(arcOval, MIN_ANGLE, MAX_ANGLE, false, backgroundPaint)
        canvas.drawArc(arcOval, MIN_ANGLE, sweepAngle, false, progressPaint)

        canvas.drawText(
            currentProgressInPresents,
            centerOfX - textPaint.measureText(currentProgressInPresents) / 2f,
            centerOfY + width * TEXT_Y_COEFFICIENT,
            textPaint
        )
    }

    private fun animateTo(progress: Float) {
        toProgress = progress.toAngle()
        progressValueAnimator.start()
    }

    //region utils
    private fun Float.toAngle() = this * MAX_ANGLE / MAX_PROGRESS_VALUE
    private fun Float.toPercents() = this * MAX_PROGRESS_VALUE / MAX_ANGLE

    private fun ValueAnimator.addOnEndListener(action: (Animator) -> Unit) {
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animator: Animator) {
                action(animator)
            }

            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
    }
    //endregion
}