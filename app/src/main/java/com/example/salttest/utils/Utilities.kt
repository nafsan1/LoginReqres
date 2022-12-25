package com.example.salttest.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


const val BASEAPI = "https://reqres.in/"


@Suppress("DEPRECATION")
fun Context.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
}
