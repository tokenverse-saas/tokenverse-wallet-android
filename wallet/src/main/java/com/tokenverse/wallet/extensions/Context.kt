package com.tokenverse.wallet.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import com.tokenverse.wallet.R

fun Context.getStyledAttributes(attributeSet: AttributeSet?, styleArray: IntArray, block: TypedArray.() -> Unit) =
  this.obtainStyledAttributes(attributeSet, styleArray, 0, 0).use(block)

val Context.grey500: Int
  get() = ContextCompat.getColor(this ,R.color.grey500)

val Context.white: Int
  get() = ContextCompat.getColor(this ,R.color.white)

val Context.neutralDarker: Int
  get() = ContextCompat.getColor(this ,R.color.neutral_darker)

val Context.headerColor: Int
  get() = ContextCompat.getColor(this ,R.color.header_default)

