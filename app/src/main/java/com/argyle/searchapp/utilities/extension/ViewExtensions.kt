package com.argyle.searchapp.utilities.extension

import android.view.View

fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}
