package com.alejandromejia.hellobuildtest.ui.main.components

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.alejandromejia.hellobuildtest.ui.main.components.bottombar.BottomBarComponent
import com.alejandromejia.hellobuildtest.ui.main.components.topbar.TopBarView
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.navigation.NavigationComponent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldMainView(viewModels: ViewModels) {

    val isFocused = viewModels.searchViewModel.usersFocused.observeAsState()

    Scaffold(
        topBar = {
            if (isFocused.value == true) Unit else TopBarView(viewModels = viewModels)
        },
        bottomBar = {
            if (isFocused.value == true) Unit else BottomBarComponent(viewModels = viewModels)
        },
        scaffoldState = rememberScaffoldState(),
        content = {
            NavigationComponent(viewModels = viewModels)
        }
    )
}