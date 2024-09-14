package com.alejandromejia.hellobuildtest.ui.main.components.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.alejandromejia.hellobuildtest.domain.utils.ScreenComponent
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels

@Composable
fun TopBarView(viewModels: ViewModels) {

    val screen by viewModels.mainViewModel.screen.observeAsState(ScreenComponent.Users)
    TopBarComponent(screen = screen, viewModels = viewModels)

}