package com.example.miniproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.miniproject.MainActivity.Companion.paintBrush
import com.example.miniproject.MainActivity.Companion.path

class paintview : View {

    var params : ViewGroup.LayoutParams? = null

    companion object{
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentbrush = Color.BLACK
    }
    constructor(context: Context) : this(context, null){
            init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
            init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
            init()
    }

    private fun init(){
        paintBrush.isAntiAlias = true
        paintBrush.color = currentbrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event?.x
        var y = event?.y

        if (event != null) {
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    if (x != null) {
                        if (y != null) {
                            path.moveTo(x,y)
                        }
                    }
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (x != null) {
                        if (y != null) {
                            path.lineTo(x,y)
                        }
                    }
                    pathList.add(path)
                    colorList.add(currentbrush)
                }
                else -> return false
            }

        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas?) {
        for(i in pathList.indices){
            paintBrush.color = colorList[i]
            if (canvas != null) {
                canvas.drawPath(pathList[i], paintBrush)
            }
            invalidate()
        }
    }






}