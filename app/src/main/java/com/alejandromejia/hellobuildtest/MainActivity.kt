package com.alejandromejia.hellobuildtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.alejandromejia.hellobuildtest.ui.favorites.viewmodel.FavoritesViewModel
import com.alejandromejia.hellobuildtest.ui.main.components.BottomSheetUsers
import com.alejandromejia.hellobuildtest.ui.main.components.ScaffoldMainView
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.main.viewmodel.MainViewModel
import com.alejandromejia.hellobuildtest.ui.search.viewmodel.SearchViewModel
import com.alejandromejia.hellobuildtest.ui.theme.HelloBuildTestTheme
import com.alejandromejia.hellobuildtest.ui.users.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModels()
        setContent {
            val isBottomSheetVisible by mainViewModel.showBottomSheet.observeAsState(initial = false)

            HelloBuildTestTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldMainView(viewModels = getViewModels())

                    if (isBottomSheetVisible) {
                        BottomSheetUsers(viewModels = getViewModels())
                    }

                }

            }
        }
    }

    private fun setupViewModels() {
        mainViewModel = viewModels<MainViewModel>().value
        usersViewModel = viewModels<UsersViewModel>().value
        searchViewModel = viewModels<SearchViewModel>().value
        favoritesViewModel = viewModels<FavoritesViewModel>().value
    }

    private fun getViewModels(): ViewModels {
        return ViewModels(
            mainViewModel = mainViewModel,
            usersViewModel = usersViewModel,
            searchViewModel = searchViewModel,
            favoritesViewModel = favoritesViewModel
        )
    }
}

