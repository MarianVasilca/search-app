package com.argyle.searchapp.utilities.extension

fun String.toCapitalLetter(): String {
    return lowercase()
        .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
}