package com.hansihe.epsilon_sim

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class KeypadButtonView(context: Context?, val btnDef: KeypadButtons.BtnDef) : View(context) {

    val mainText = TextView(context)

    val fontMetrics: Paint.FontMetrics = Paint.FontMetrics()
    val rect: Rect = Rect()

    val mainTextPaint: Paint = Paint()
    val shiftTextPaint: Paint = Paint()
    val alphaTextPaint: Paint = Paint()

    init {
        mainTextPaint.color = Color.GRAY
        mainTextPaint.textAlign = Paint.Align.CENTER

        shiftTextPaint.color = Color.parseColor("#F8B430")
        shiftTextPaint.textAlign = Paint.Align.LEFT

        alphaTextPaint.color = Color.LTGRAY
        alphaTextPaint.textAlign = Paint.Align.RIGHT
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        mainTextPaint.textSize = width / 4f
        drawTextCentered(canvas, mainTextPaint, btnDef.main)

        if (btnDef.shift != null) {
            shiftTextPaint.textSize = width / 6f

            shiftTextPaint.getTextBounds(btnDef.shift, 0, btnDef.shift.length, rect)
            val tHeight = rect.height()

            canvas.drawText(
                    btnDef.shift,
                    width / 10f,
                    tHeight + (width / 10f),
                    shiftTextPaint
            )
        }

        if (btnDef.alpha != null) {
            alphaTextPaint.textSize = width / 6f

            alphaTextPaint.getTextBounds(btnDef.alpha, 0, btnDef.alpha.length, rect)
            val tHeight = rect.height()

            canvas.drawText(
                    btnDef.alpha,
                    width - (width / 10f),
                    tHeight + (width / 10f),
                    alphaTextPaint
            )
        }
    }

    fun drawTextCentered(canvas: Canvas, paint: Paint, text: String) {
        paint.getFontMetrics(fontMetrics)

        paint.getTextBounds(text, 0, text.length, rect)
        val tHeight = rect.height()

        canvas.drawText(
                text,
                (width / 2f),
                (height / 2f) + (tHeight / 2f),
                paint
        )
    }
}