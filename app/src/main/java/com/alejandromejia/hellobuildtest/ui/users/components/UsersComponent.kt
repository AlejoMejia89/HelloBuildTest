package com.alejandromejia.hellobuildtest.ui.users.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.utils.NoRippleInteractionSource
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.users.model.UsersSortedView
import com.alejandromejia.hellobuildtest.utils.SORTED_BY

@Composable
fun UsersComponent(
    users: List<DUser>,
    viewModels: ViewModels,
    totalPages: Int,
    modifier: Modifier,
    page: Int,
    sortedView: UsersSortedView?
) {
    if (users.isNotEmpty()) {
        UsersPagination(
            viewModels = viewModels,
            modifier = modifier,
            page = page,
            totalPages = totalPages
        )
        users.forEach { user ->
            UserItems(
                user = user,
                viewModels = viewModels
            )
        }
    }

    if (sortedView?.isSorted == true) {
        Surface(
            color = Color.White,
            modifier = modifier
                .wrapContentSize()
                .height(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy((-5).dp)
            ) {
                Text(
                    text = "$SORTED_BY ${sortedView.sortedValue}",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Blue
                )
                IconButton(onClick = {
                    viewModels.usersViewModel.setSortList(false)
                    viewModels.usersViewModel.getOriginalList()
                }, interactionSource = NoRippleInteractionSource()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = Color.Black
                    )
                }
            }

        }
    }
}