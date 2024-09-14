package com.alejandromejia.hellobuildtest.ui.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import com.alejandromejia.hellobuildtest.domain.utils.ScreenComponent
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.theme.HyperLightGray
import com.alejandromejia.hellobuildtest.utils.CANCEL
import com.alejandromejia.hellobuildtest.utils.EMPTY_STRING
import com.alejandromejia.hellobuildtest.utils.SEARCH

@Composable
fun SearchComponent(viewModels: ViewModels) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val screen = viewModels.mainViewModel.screen.observeAsState(ScreenComponent.Users)
    val query = viewModels.searchViewModel.userQuery.observeAsState(EMPTY_STRING).value
    val focused = viewModels.searchViewModel.usersFocused.observeAsState(false)

    var onSearchFocusChange by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 1.dp, start = 1.dp, top = 8.dp, bottom = 2.dp)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            modifier = Modifier
                .then(
                    Modifier
                        .height(45.dp)
                        .padding(
                            start = 16.dp,
                            top = 8.dp, bottom = 6.dp,
                            end = if (!focused.value) 16.dp else 10.dp
                        )
                )
                .weight(1f),
            color = HyperLightGray,
            shape = RoundedCornerShape(percent = 25),
        ) {

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                ) {

                    SearchHintTopBar(Modifier.padding(start = 10.dp, end = 8.dp), query)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BasicTextField(
                            value = query,
                            onValueChange = {
                                viewModels.searchViewModel.setQuery(
                                    query = it
                                )
                            },
                            modifier = Modifier
                                .wrapContentHeight()
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .onFocusChanged {
                                    onSearchFocusChange = it.isFocused
                                }
                                .focusRequester(focusRequester)
                                .padding(start = 31.dp, end = 8.dp),
                            singleLine = true,
                            interactionSource = remember { MutableInteractionSource() }
                                .also { interactionSource ->
                                    LaunchedEffect(interactionSource) {
                                        interactionSource.interactions.collect {
                                            if (it is PressInteraction.Release) {
                                                viewModels.searchViewModel.getRecentSearch()
                                                viewModels.searchViewModel.isFocused(
                                                    focused = true
                                                )
                                            }
                                        }
                                    }
                                },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(onSearch = {
                                viewModels.searchViewModel.saveRecentSearch(
                                    DRecentSearch(
                                        id = null,
                                        name = query
                                    )
                                )
                                when (screen.value) {
                                    ScreenComponent.Favorites -> {}
                                    ScreenComponent.Users -> {
                                        viewModels.usersViewModel.getUserByName(query)
                                    }
                                }
                                viewModels.searchViewModel.isFocused(
                                    focused = false
                                )
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }),
                            cursorBrush = SolidColor(Color.Gray)
                        )
                    }
                }
            }

        }

        if (focused.value) {
            Surface(
                modifier = Modifier
                    .weight(0.2f)
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp),
                color = Color.White
            ) {
                AnimatedVisibility(
                    visible = true,
                    enter = slideInHorizontally(animationSpec = tween(durationMillis = 10000)) { fullWidth ->
                        -fullWidth / 3
                    } + fadeIn(
                        animationSpec = tween(durationMillis = 10000)
                    ),
                    exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.DampingRatioLowBouncy)) {
                        10000
                    } + fadeOut()
                ) {
                    Text(
                        text = CANCEL,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                when (screen.value) {
                                    ScreenComponent.Favorites -> viewModels.usersViewModel.getOriginalList()
                                    ScreenComponent.Users -> viewModels.usersViewModel.getOriginalList()
                                }
                                viewModels.searchViewModel.isFocused(
                                    focused = false,
                                )
                                viewModels.searchViewModel.setQuery(EMPTY_STRING)
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchHintTopBar(modifier: Modifier = Modifier, query: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(modifier)

    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xff757575)
        )
        if (query.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 5.dp),
                color = Color(0xff757575),
                text = SEARCH,
            )
        }
    }
}