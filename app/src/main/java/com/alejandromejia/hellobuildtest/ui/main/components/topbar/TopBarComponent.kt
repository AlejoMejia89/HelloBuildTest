package com.alejandromejia.hellobuildtest.ui.main.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alejandromejia.hellobuildtest.domain.utils.ScreenComponent
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.utils.ColorsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(screen: ScreenComponent, viewModels: ViewModels) {

    TopAppBar(
        title = {
            Text(
                fontSize = 30.sp,
                text = screen.title,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        navigationIcon = { if (screen.navigationIcon) SelectNavigationIcon() },
        actions = { if (screen.actionIcon) SelectActionIcons(screen, viewModels = viewModels) },
        colors = ColorsTopBar
    )
}

@Composable
fun SelectActionIcons(screen: ScreenComponent, viewModels: ViewModels) {

    IconButton(onClick = { }) {
        screen.actionIcon1?.let { Icon(imageVector = it, contentDescription = null) }
    }
    IconButton(onClick = { viewModels.mainViewModel.showBottomSheet(true) }) {
        screen.actionIcon2?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
    }

}

@Composable
fun SelectNavigationIcon() {

    IconButton(onClick = { }) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
    }
}