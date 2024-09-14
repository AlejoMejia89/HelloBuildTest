package com.alejandromejia.hellobuildtest.ui.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels

@Composable
fun ItemsRecentSearch(
    item: DRecentSearch,
    viewModels: ViewModels
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        val (iconSearch, textComponent, iconClose) = createRefs()
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .constrainAs(iconSearch) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(20.dp)
        )
        Text(
            text = item.name,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textComponent) {
                    start.linkTo(iconSearch.end)
                    top.linkTo(parent.top)
                }
                .padding(start = 10.dp)
                .clickable {
                    viewModels.searchViewModel.setQuery(query = item.name)
                    viewModels.usersViewModel.getUserByName(item.name)
                    viewModels.searchViewModel.isFocused(false)
                    focusManager.clearFocus()
                    keyboardController?.hide()
                },
            textAlign = TextAlign.Start
        )
        IconButton(
            onClick = {
                viewModels.searchViewModel.removeRecentSearch(item)
                viewModels.searchViewModel.getRecentSearch()
            },
            modifier = Modifier
                .constrainAs(iconClose) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .size(20.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}