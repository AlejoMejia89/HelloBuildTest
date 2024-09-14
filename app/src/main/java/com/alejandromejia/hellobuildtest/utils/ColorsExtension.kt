package com.alejandromejia.hellobuildtest.utils

import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.graphics.Color
import com.alejandromejia.hellobuildtest.R


@OptIn(ExperimentalMaterial3Api::class)
val ColorsTopBar = TopAppBarColors(
    containerColor = Color.White,
    actionIconContentColor = Color.Black,
    titleContentColor = Color.Black,
    navigationIconContentColor = Color.Black,
    scrolledContainerColor = Color.White
)

fun getResourceColor(id: Int): Int {
    return when (id) {
        1 -> R.color.first_user
        2 -> R.color.second_user
        3 -> R.color.third_user
        4 -> R.color.fourth_user
        5 -> R.color.fifth_user
        6 -> R.color.sixth_user
        7 -> R.color.seventh_user
        8 -> R.color.eighth_user
        9 -> R.color.ninth_user
        10 -> R.color.tenth_user
        else -> R.color.another_user
    }
}

fun getCardColors(): CardColors {
    return CardColors(
        containerColor = Color.White,
        contentColor = Color.Black,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.Gray
    )
}