package com.example.demo.ui.toolkit

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes


fun Context.dpToPixels(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun Context.dpToPixels(value: Float): Int = (value * resources.displayMetrics.density).toInt()

fun Context.spToPixels(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.spToPixels(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

fun View.dpToPixels(value: Int): Int = context.dpToPixels(value)
fun View.dpToPixels(value: Float): Int = context.dpToPixels(value)
fun View.spToPixels(value: Int): Int = context.spToPixels(value)
fun View.spToPixels(value: Float): Int = context.spToPixels(value)
fun View.px2dp(px: Int): Float = context.px2dp(px)
fun View.px2sp(px: Int): Float = context.px2sp(px)
fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

/**
 * Return the color with the given alpha value.
 * Examples:
 *   0xabcdef.withAlpha(0xCF) == 0xCFabcdef
 *   0xFFabcdef.withAlpha(0xCF) == 0xCFabcdef
 *
 * @param alpha the alpha channel value: [0x0..0xFF].
 * @return the color with the given alpha value applied.
 */
fun Int.withAlpha(alpha: Int): Int {
    require(alpha in 0..0xFF)
    return this and 0x00FFFFFF or (alpha shl 24)
}