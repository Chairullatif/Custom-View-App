package com.khoirullatif.customviewapp.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.khoirullatif.customviewapp.R

class MyButton : AppCompatButton {

    private var colorText: Int = 0
    private var enabledBgButton: Drawable? = null
    private var disabledBgButon: Drawable? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setTextColor(colorText)
        textSize = 12f
        gravity = Gravity.CENTER
        text = when {
            isEnabled -> "Submit"
            else -> "Isi dulu"
        }
        background = when {
            isEnabled -> enabledBgButton
            else -> disabledBgButon
        }
    }

    private fun init() {
        colorText = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBgButton = ResourcesCompat.getDrawable(resources, R.drawable.bg_button, null)
        disabledBgButon = ResourcesCompat.getDrawable(resources, R.drawable.bg_button_disable, null)
    }
}