package com.matveyaka.cameramv.scanActivity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.Size
import android.view.View
import java.util.jar.Attributes

class DrawView(
    private val context: Context,
    private val attributes: AttributeSet
    ): View(context, attributes){

    private var viewSize = Size(0, 0)
    private var mainPaint = Paint (ANTI_ALIAS_FLAG)

    init {
        mainPaint.color = Color.RED
        mainPaint.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewSize = Size (w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(
            viewSize.width.toFloat() / 2,
            viewSize.height.toFloat() /2,
            40F,
            mainPaint
        )
    }
}