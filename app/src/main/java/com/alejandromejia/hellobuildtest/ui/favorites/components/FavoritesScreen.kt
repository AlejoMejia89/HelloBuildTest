package com.alejandromejia.hellobuildtest.ui.favorites.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.hellobuildtest.R
import com.alejandromejia.hellobuildtest.ui.favorites.model.FavoriteItemsView
import com.alejandromejia.hellobuildtest.ui.main.components.EmptyScreen
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.theme.HyperLightGray
import com.alejandromejia.hellobuildtest.utils.NO_FAVORITES

@Composable
fun FavoritesScreen(viewModels: ViewModels, listState: ScrollState, data: FavoriteItemsView?) {

    val favorites = data?.users ?: emptyList()
    val letters = favorites.groupBy { it.name.first().uppercase() }

    Column(
        modifier = Modifier
            .verticalScroll(state = listState)
            .padding(bottom = 55.dp)
            .fillMaxSize()
    ) {
        if (favorites.isEmpty()) {
            EmptyScreen(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = NO_FAVORITES,
                resource = R.drawable.ic_favorites_empty
            )
        } else {
            letters.forEach { (letter, users) ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HyperLightGray)
                ) {
                    Text(
                        text = letter, modifier = Modifier
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }

                users.forEach { user ->
                    FavoriteItems(viewModels = viewModels, user = user)
                }

            }
        }

    }
}
