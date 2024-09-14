package com.alejandromejia.hellobuildtest.ui.users.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alejandromejia.hellobuildtest.R
import com.alejandromejia.hellobuildtest.domain.utils.NoRippleInteractionSource
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.users.model.UsersItemsView
import com.alejandromejia.hellobuildtest.utils.EMPTY_STRING
import com.alejandromejia.hellobuildtest.utils.OF

@Composable
fun UsersPagination(
    viewModels: ViewModels,
    modifier: Modifier,
    page: Int,
    totalPages: Int
) {

    Row(modifier = modifier.padding(top = 20.dp)) {
        IconButton(onClick = {
            viewModels.usersViewModel.nextPage(1)
        }, enabled = page > 1, interactionSource = NoRippleInteractionSource()) {
            if (page > 1) {
                Icon(
                    painter = painterResource(id = R.drawable.first_element_arrow),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        IconButton(onClick = {
            viewModels.usersViewModel.nextPage(page - 1)
        }, enabled = page > 1, interactionSource = NoRippleInteractionSource()) {
            if (page > 1) {
                Icon(
                    painter = painterResource(id = R.drawable.prev_element_arrow),
                    contentDescription = EMPTY_STRING,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Text(
            text = "$page $OF $totalPages",
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            color = Color.Black
        )


        IconButton(onClick = {
            viewModels.usersViewModel.nextPage(page + 1)
        }, enabled = page < totalPages, interactionSource = NoRippleInteractionSource()) {
            if (page < totalPages) {
                Icon(
                    painter = painterResource(id = R.drawable.next_element_arrow),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        IconButton(onClick = {
            viewModels.usersViewModel.nextPage(totalPages)
        }, enabled = page < totalPages, interactionSource = NoRippleInteractionSource()) {
            if (page < totalPages) {
                Icon(
                    painter = painterResource(id = R.drawable.last_element_arrow),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

}