package com.example.keklist

import android.widget.EditText

fun String.doesContain(string: String) : Boolean {
    return trim().toLowerCase().contains(string.trim().toLowerCase())
}

fun String.isGoods() : Boolean {
    return equals("Товары")
}

fun String.isServices() : Boolean {
    return equals("Услуги")
}