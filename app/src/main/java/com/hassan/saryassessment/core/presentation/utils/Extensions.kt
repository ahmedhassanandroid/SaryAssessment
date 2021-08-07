package com.hassan.saryassessment.core.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.Toast

fun Drawable.drawableToBitmap(): Bitmap? {
    var bitmap: Bitmap? = null
    if (this is BitmapDrawable) {
        if (this.bitmap != null) {
            return this.bitmap
        }
    }
    bitmap = if (this.intrinsicWidth <= 0 || this.intrinsicHeight <= 0) {
        Bitmap.createBitmap(
            1,
            1,
            Bitmap.Config.ARGB_8888
        ) // Single color bitmap will be created of 1x1 pixel
    } else {
        Bitmap.createBitmap(
            this.intrinsicWidth,
            this.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
    }
    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, canvas.width, canvas.height)
    this.draw(canvas)
    return bitmap
}

fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Float.getDpValue(context: Context): Int{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics).toInt()
}

fun Int.getPixelValue(context: Context): Int{
    return context.resources.getDimensionPixelSize(this)
}