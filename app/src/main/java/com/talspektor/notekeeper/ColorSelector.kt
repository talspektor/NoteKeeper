package com.talspektor.notekeeper

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.color_selector.view.*

class ColorSelector @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private  var listOfColors =  listOf(Color.BLUE, Color.GREEN)
    private var selectedColorIndex = 0
    init {
        val typedArray = context.obtainStyledAttributes(
            attributeSet, R.styleable.ColorSelector
        )
        listOfColors = typedArray.getTextArray(R.styleable.ColorSelector_colors)
            .map {
                Color.parseColor(it.toString())
            }
        typedArray.recycle()

        orientation = HORIZONTAL
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.color_selector, this)

        selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])

        colorSelectorArrowLeft.setOnClickListener {
            selectPreviousColor()
        }

        colorSelectorArrowRight.setOnClickListener {
            selectNextColor()
        }

        colorEnabled.setOnCheckedChangeListener { buttonView, isChecked ->
            broadcastColor()
        }
    }

    var selectedColorValue: Int = android.R.color.transparent
        set(value) {
            var index = listOfColors.indexOf(value)
            if (index == -1) {
                colorEnabled.isChecked = false
                index = 0
            } else {
                colorEnabled.isChecked = true
            }
            selectedColorIndex = index
            selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])
        }

    private var colorSelectListeners: ArrayList<(Int) -> Unit> = arrayListOf()

    fun addListener(function: (Int) -> Unit) {
        this.colorSelectListeners.add(function)
    }

    private fun selectPreviousColor() {
        if (selectedColorIndex == 0) {
            selectedColorIndex = listOfColors.lastIndex
        } else {
            selectedColorIndex--
        }
        selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])
        broadcastColor()
    }

    private fun selectNextColor() {
        if (selectedColorIndex == listOfColors.lastIndex) {
            selectedColorIndex = 0
        } else {
            selectedColorIndex++
        }
        selectedColor.setBackgroundColor(listOfColors[selectedColorIndex])
        broadcastColor()
    }

    private fun broadcastColor(){
        val color = if (colorEnabled.isChecked)
            listOfColors[selectedColorIndex]
        else
            Color.TRANSPARENT
        this.colorSelectListeners.forEach { function ->
            function(color)
        }
    }
}