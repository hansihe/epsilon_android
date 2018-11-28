package com.hansihe.epsilon_sim

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import kotlin.math.abs
import kotlin.math.min

class JoypadView(ctx: Context) : View(ctx) {

    enum class Event {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        PRESS
    }

    var actionListener: ((Event) -> Unit)? = null

    val mainPaint = Paint()
    val bgPaint = Paint()

    var dragActive = false
    var performedDrag = false
    var lastActionX = 0.0f
    var lastActionY = 0.0f

    val requiredDistance = 80

    init {
        mainPaint.color = Color.parseColor("#dddddd")
        bgPaint.color = Color.WHITE
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dragActive = true
                performedDrag = false
                lastActionX = event.x
                lastActionY = event.y
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (!performedDrag and ((event.eventTime - event.downTime) < 125)) {
                    handleAction(Event.PRESS)
                }
                dragActive = false
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(lastActionX - event.x) > requiredDistance) {
                    if (lastActionX - event.x > 0) handleAction(Event.LEFT)
                    else handleAction(Event.RIGHT)

                    lastActionX = event.x
                    lastActionY = event.y
                    performedDrag = true
                }
                if (abs(lastActionY - event.y) > requiredDistance) {
                    if (lastActionY - event.y > 0) handleAction(Event.UP)
                    else handleAction(Event.DOWN)

                    lastActionX = event.x
                    lastActionY = event.y
                    performedDrag = true
                }
            }
        }
        return false
    }

    fun handleAction(event: Event) {
        actionListener!!(event)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        val minDim = min(width, height)

        val cX = width / 2f
        val cY = height / 2f

        canvas.drawCircle(cX, cY, minDim * 0.3f, mainPaint)
        canvas.drawCircle(cX, cY, minDim * 0.27f, bgPaint)
        canvas.drawCircle(cX, cY, minDim * 0.2f, mainPaint)

    }

}