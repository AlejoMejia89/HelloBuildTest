package com.alejandromejia.hellobuildtest.ui.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.search.model.RecentSearchView
import com.alejandromejia.hellobuildtest.utils.RECENT_SEARCH

@Composable
fun RecentSearchScreenView(
    viewModels: ViewModels,
    recentSearch: RecentSearchView?
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = RECENT_SEARCH,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold
            )
            if (recentSearch?.recentSearch?.isEmpty() == false) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 25.dp, horizontal = 5.dp),
                ) {
                    items(recentSearch.recentSearch) { item ->
                        ItemsRecentSearch(
                            item = item,
                            viewModels = viewModels
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}