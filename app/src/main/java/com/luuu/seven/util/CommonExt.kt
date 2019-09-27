package com.luuu.seven.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.os.ParcelCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.luuu.seven.ComicApplication
import java.io.Serializable

fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity
fun Context.dimen2px(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

fun Context.getVersionName(): String = packageManager.getPackageInfo(packageName, 0).versionName
fun Context.getVersionCode(): Int = packageManager.getPackageInfo(packageName, 0).versionCode
fun Context.getAppName(): String =
    resources.getString(packageManager.getPackageInfo(packageName, 0).applicationInfo.labelRes)

fun Context.inflateLayout(@LayoutRes layoutId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View =
    LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

fun View.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun View.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()
fun View.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun View.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun View.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun View.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity
fun View.dimen2px(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

fun Fragment.dp2px(value: Int): Int = context!!.dp2px(value)
fun Fragment.px2dp(value: Int): Float = context!!.px2dp(value)
fun Fragment.sp2px(value: Float): Int = context!!.sp2px(value)
fun Fragment.px2sp(value: Int): Float = context!!.px2sp(value)


fun toast(msg: String? = "error", duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ComicApplication.mApp, msg, duration).show()
}

fun toast(@StringRes msg: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ComicApplication.mApp, ComicApplication.mApp.resources.getString(msg), duration).show()
}

fun Parcel.writeBooleanUsingCompat(value: Boolean) = ParcelCompat.writeBoolean(this, value)

fun Parcel.readBooleanUsingCompat() = ParcelCompat.readBoolean(this)

val <T> T.checkAllMatched: T
    get() = this

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.dismissKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Intent.putExtras(vararg params: Pair<String, Any>): Intent {
    if (params.isEmpty()) return this
    params.forEach { (key, value) ->
        when (value) {
            is Int -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Bundle -> putExtra(key, value)
            is String -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
            is ShortArray -> putExtra(key, value)
            is DoubleArray -> putExtra(key, value)
            is BooleanArray -> putExtra(key, value)
            is CharSequence -> putExtra(key, value)
            is Array<*> -> {
                when {
                    value.isArrayOf<String>() ->
                        putExtra(key, value as Array<String?>)
                    value.isArrayOf<Parcelable>() ->
                        putExtra(key, value as Array<Parcelable?>)
                    value.isArrayOf<CharSequence>() ->
                        putExtra(key, value as Array<CharSequence?>)
                    else -> putExtra(key, value)
                }
            }
            is Serializable -> putExtra(key, value)
        }
    }
    return this
}

fun <T> Intent.get(key: String): T? {
    try {
        val extras = IntentFieldMethod.mExtras.get(this) as Bundle
        IntentFieldMethod.unparcel.invoke(extras)
        val map = IntentFieldMethod.mMap.get(extras) as Map<String, Any>
        return map[key] as T
    } catch (e: Exception) {
    }
    return null
}

