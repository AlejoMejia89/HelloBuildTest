package com.alejandromejia.hellobuildtest.utils

fun String.getLettersByName(): String {
    val split = this.split(" ").take(2)
    var result = EMPTY_STRING
    for (i in split) {
        result += i[0]
    }
    return result
}