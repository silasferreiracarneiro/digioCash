package br.com.silas.digiocash.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

fun String.getColoredString(color: Int): Spannable {
    val spannable: Spannable = SpannableString(this)
    spannable.setSpan(
        ForegroundColorSpan(color),
        0,
        spannable.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannable
}