package com.alejandromejia.hellobuildtest.ui.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.usecases.favorites.GetFavoritesUseCase
import com.alejandromejia.hellobuildtest.domain.usecases.favorites.RemoveFavoritesUseCase
import com.alejandromejia.hellobuildtest.domain.usecases.favorites.SaveFavoriteUseCase
import com.alejandromejia.hellobuildtest.ui.favorites.model.FavoriteItemsView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val removeFavoritesUseCase: RemoveFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _favoriteItemsView: MutableLiveData<FavoriteItemsView> =
        MutableLiveData<FavoriteItemsView>()
    val favoriteItemsView: LiveData<FavoriteItemsView> get() = _favoriteItemsView

    init {
        getFavorites()
    }

    fun saveFavorite(user: DUser) {
        viewModelScope.launch {
            saveFavoriteUseCase.invoke(
                model = user
            )
        }

    }

    fun removeFavorite(user: DUser) {
        viewModelScope.launch {
            removeFavoritesUseCase.invoke(
                model = user
            )
        }
        getFavorites()
    }

    fun getFavorites() {
        _favoriteItemsView.value = FavoriteItemsView(
            loading = true,
            users = emptyList()
        )
        viewModelScope.launch {
            _favoriteItemsView.value = FavoriteItemsView(
                loading = false,
                users = getFavoritesUseCase()
            )
        }
    }
}