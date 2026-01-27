package com.app.compulynx.utils

fun Double.format(): String {
    val number = this
    val formatter = java.text.DecimalFormat("#,###.00")
    return formatter.format(number)
}