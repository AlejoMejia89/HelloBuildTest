package com.alejandromejia.hellobuildtest.utils

fun Int.isZeroInt(): Boolean = this == 0

fun Int.isNotZeroInt(): Boolean = this != 0

fun Int?.orZeroInt(): Int = this ?: 0

fun Int.isPositiveInt(): Boolean = this >= 0