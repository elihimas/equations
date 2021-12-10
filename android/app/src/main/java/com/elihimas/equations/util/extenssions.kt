package com.elihimas.equations.util

import android.view.View
import android.widget.Button

fun Button.updateTextAndTag(option: Int) {
    text = option.toString()
    tag = option
}

fun View.setOnClickListener(function: () -> Unit) {
    setOnClickListener { source ->
        function.invoke()
    }
}

fun View.disable() {
    isEnabled = false
}