package com.alejandromejia.hellobuildtest.ui.users.model

import com.alejandromejia.hellobuildtest.utils.EMPTY_STRING

data class UsersSortedView(
    val isSorted: Boolean = false,
    val sortedValue: String = EMPTY_STRING
)
