package com.oazisn.moviecatalog.core.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Looper
import android.util.TypedValue
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(@StringRes res: Int, @IdRes anchor: Int) {
    Snackbar.make(this.context, this, resources.getString(res), Snackbar.LENGTH_SHORT)
        .setAnchorView(anchor)
        .show()
}

fun View.showSnackbar(@StringRes res: Int) {
    try {
        Snackbar.make(this, resources.getString(res), Snackbar.LENGTH_SHORT)
            .show()
    }
    catch (e: IllegalArgumentException){
        e.printStackTrace()
    }
}

fun Context.isConnected(): Boolean {
    val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    var result = false
    if (activeNetwork != null) {
        result = activeNetwork.isConnectedOrConnecting
    }
    return result
}

fun Context.getDrawableFromAttribute(attributeId: Int): Drawable? {
    val typedValue = TypedValue().also { theme.resolveAttribute(attributeId, it, true) }
    return ContextCompat.getDrawable(this, typedValue.resourceId)
}