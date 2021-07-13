package com.khoirullatif.customviewapp.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.khoirullatif.customviewapp.R

class MyEditText : AppCompatEditText, View.OnTouchListener {

    internal lateinit var clearButton: Drawable

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
        hint = "Masukkan nama Anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }


    // kelas ini digunakan untuk menentukan behaviour dr komponen customView
    private fun init() {
        clearButton = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_24, null) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }
        })
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        // Mengatasi ketika clearButton ditekan

        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false
            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (clearButton.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < clearButtonEnd -> isClearButtonClicked = true
                    }
                }
                else -> {
                    clearButtonStart = (width - paddingEnd - clearButton.intrinsicWidth).toFloat()
                    when {
                        event.x > clearButtonStart -> isClearButtonClicked = true
                    }
                }
            }

            when {
                isClearButtonClicked -> when {
                    event.action == MotionEvent.ACTION_DOWN -> { // ACTION DOWN ini ketika tombol ditekan
                        clearButton = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_24, null) as Drawable
                        showClearButton()
                        return true
                    }
                    event.action == MotionEvent.ACTION_UP -> { // ACTION UP ini ketika tombol selesai ditekan (atau setelah dilepas)
                        clearButton = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_24, null) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }
                    else -> return false
                }
                else -> return false
            }
        }
        return false
    }

    private fun showClearButton() {
        // setCompoundDrawablesWithIntrinsicBounds digunakan untuk menampilkan gambar pd editText
        setCompoundDrawablesWithIntrinsicBounds(null, null, clearButton, null)
    }

    private fun hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }
}