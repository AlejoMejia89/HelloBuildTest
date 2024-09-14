package com.alejandromejia.hellobuildtest.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import com.alejandromejia.hellobuildtest.domain.usecases.search.GetRecentSearchUseCase
import com.alejandromejia.hellobuildtest.domain.usecases.search.RemoveRecentSearchUseCase
import com.alejandromejia.hellobuildtest.domain.usecases.search.SaveRecentSearchUseCase
import com.alejandromejia.hellobuildtest.domain.utils.ScreenComponent
import com.alejandromejia.hellobuildtest.ui.search.model.RecentSearchView
import com.alejandromejia.hellobuildtest.ui.users.model.UsersItemsView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val saveRecentSearchUseCase: SaveRecentSearchUseCase,
    private val removeRecentSearchUseCase: RemoveRecentSearchUseCase,
    private val getRecentSearchUseCase: GetRecentSearchUseCase
) : ViewModel() {

    private val _usersFocused: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>()
    val usersFocused: LiveData<Boolean> get() = _usersFocused

    private val _userQuery: MutableLiveData<String> =
        MutableLiveData<String>()
    val userQuery: LiveData<String> get() = _userQuery

    private val _recentSearch: MutableLiveData<RecentSearchView> =
        MutableLiveData<RecentSearchView>()
    val recentSearch: LiveData<RecentSearchView> get() = _recentSearch

    fun isFocused(focused: Boolean) {
        _usersFocused.value = focused
    }

    fun setQuery(query: String) {
        _userQuery.value = query
    }

    fun saveRecentSearch(model: DRecentSearch) {
        viewModelScope.launch {
            saveRecentSearchUseCase(model)
        }
    }

    fun removeRecentSearch(model: DRecentSearch) {
        viewModelScope.launch {
            removeRecentSearchUseCase(model)
        }
    }

    fun getRecentSearch() {
        viewModelScope.launch {
            _recentSearch.value = RecentSearchView(
                recentSearch = getRecentSearchUseCase(),
                loading = false
            )
        }
    }

}