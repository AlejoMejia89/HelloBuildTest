package com.alejandromejia.hellobuildtest.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun EmptyScreen(modifier: Modifier, text: String, resource: Int) {
    val config = LocalConfiguration.current
    Box(
        modifier = modifier
            .height(config.screenHeightDp.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Icon(
                painter = painterResource(id = resource),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(60.dp)
                    .padding(bottom = 5.dp)
            )
            Text(
                text = text,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}