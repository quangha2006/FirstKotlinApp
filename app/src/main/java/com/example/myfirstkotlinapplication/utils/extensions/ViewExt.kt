package com.example.myfirstkotlinapplication.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.text.method.PasswordTransformationMethod
import android.text.method.ReplacementTransformationMethod
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

private val duration = 750L
private inline val interpolator: Interpolator
    get() = AccelerateDecelerateInterpolator()

fun View.setPaddingLeft(value: Int) = setPadding(value, paddingTop, paddingRight, paddingBottom)

fun View.setPaddingRight(value: Int) = setPadding(paddingLeft, paddingTop, value, paddingBottom)


fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

fun View.setWidth(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}

fun View.resize(width: Int, height: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = width
        lp.height = height
        layoutParams = lp
    }
}

inline val ViewGroup.children: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

operator fun ViewGroup.get(index: Int): View {
    return getChildAt(index)
}

fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.deleteLine() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.bold(isBold: Boolean = true) {
    paint.isFakeBoldText = isBold
    paint.isAntiAlias = true
}

@Suppress("UNCHECKED_CAST")
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }

@Suppress("UNCHECKED_CAST")
fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }

fun View.visiable() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

inline fun View.visiableIf(block: () -> Boolean) {
    if (visibility != View.VISIBLE && block()) {
        visibility = View.VISIBLE
    }
}

fun View.invisiable() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

inline fun View.invisiableIf(block: () -> Boolean) {
    if (visibility != View.INVISIBLE && block()) {
        visibility = View.INVISIBLE
    }
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

inline fun View.goneIf(block: () -> Boolean) {
    if (visibility != View.GONE && block()) {
        visibility = View.GONE
    }
}

var EditText.value: String
    get() = text.toString()
    set(value) = setText(value)

fun EditText.getInput(): String? {
    return if (this.value.isNotBlank()) this.value else null
}

fun View.getBitmap(): Bitmap {
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    draw(canvas)
    canvas.save()
    return bmp
}

fun EditText.uppercase() {
    transformationMethod = object : ReplacementTransformationMethod() {
        private val lower = charArrayOf(
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
        )
        private val upper = charArrayOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z'
        )

        override fun getOriginal() = lower

        override fun getReplacement() = upper
    }
}

fun EditText.lowercase() {
    transformationMethod = object : ReplacementTransformationMethod() {
        private val lower = charArrayOf(
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
        )
        private val upper = charArrayOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z'
        )

        override fun getOriginal() = upper

        override fun getReplacement() = lower
    }
}

fun EditText.passwordToggledVisible() {
    val selection = selectionStart
    transformationMethod =
        if (transformationMethod == null) PasswordTransformationMethod() else null
    setSelection(selection)
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isGone() = visibility == View.GONE

fun View.isInvisible() = visibility == View.INVISIBLE

/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}
