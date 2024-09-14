package com.alejandromejia.hellobuildtest.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.hellobuildtest.data.remote.network.Failure
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.models.user.DUsersResponse
import com.alejandromejia.hellobuildtest.domain.usecases.favorites.GetFavoritesUseCase
import com.alejandromejia.hellobuildtest.domain.usecases.user.UsersUseCase
import com.alejandromejia.hellobuildtest.ui.users.model.UsersItemsView
import com.alejandromejia.hellobuildtest.ui.users.model.UsersSortedView
import com.alejandromejia.hellobuildtest.utils.EMAIL
import com.alejandromejia.hellobuildtest.utils.EMPTY_STRING
import com.alejandromejia.hellobuildtest.utils.NAME
import com.alejandromejia.hellobuildtest.utils.PHONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _usersItemsView =
        MutableLiveData<UsersItemsView>()
    val usersItemsView: LiveData<UsersItemsView> get() = _usersItemsView

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _page: MutableLiveData<Int> =
        MutableLiveData<Int>()
    val page: LiveData<Int> get() = _page

    private val _isSorted: MutableLiveData<UsersSortedView> =
        MutableLiveData<UsersSortedView>()
    val isSorted: LiveData<UsersSortedView> get() = _isSorted

    private var originalList = mapOf<Int, List<DUser>>()
    private var favoriteAction = false

    init {
        getUsers()
    }

    fun getUsers() {
        favoriteAction = !favoriteAction
        _isSorted.value = UsersSortedView(isSorted = false, sortedValue = EMPTY_STRING)
        _usersItemsView.value = UsersItemsView(
            loading = true,
            users = emptyMap()
        )
        viewModelScope.launch {
            usersUseCase
                .invoke()
                .fold(::handleFailure, ::handleSuccess)
        }
    }

    fun nextPage(page: Int) {
        _page.value = page
    }

    private fun handleFailure(failure: Failure) {
        _usersItemsView.value =
            UsersItemsView(
                errorMessage = failure.toString(),
                loading = false,
                users = processSuccessData(emptyList())
            )
        _errorMessage.value = failure.toString()
    }

    private fun handleSuccess(results: DUsersResponse) {
        _usersItemsView.value = when (results.response.isEmpty()) {
            true -> {
                UsersItemsView(
                    isEmpty = true,
                    loading = false,
                    users = processSuccessData(emptyList())
                )
            }

            false -> {
                UsersItemsView(
                    isEmpty = false,
                    users = validateFavorites(results.response),
                    loading = false
                )
            }
        }
        originalList = processSuccessData(results.response)
    }

    private fun validateFavorites(response: List<DUser>): Map<Int, List<DUser>> {
        var favorites = listOf<DUser>()
        val ids = mutableListOf<Int>()
        viewModelScope.launch {
            favorites = getFavoritesUseCase()
        }

        response.forEach { user ->
            ids.add(user.id)
        }

        favorites.forEach { user ->
            if (ids.contains(user.id)) {
                response.map {
                    if (it.id == user.id) {
                        it.isFavorite = true
                    }
                }
            }
        }

        return processSuccessData(response)
    }

    private fun processSuccessData(response: List<DUser>): Map<Int, List<DUser>> {
        return response.chunked(4).withIndex().associate { (index, chunk) ->
            index to chunk
        }
    }

    fun sortUsers(filter: String) {
        val list = usersItemsView.value?.users?.values?.flatten()?.sortedBy {
            when (filter) {
                NAME -> it.name
                EMAIL -> it.email
                PHONE -> it.phone
                else -> it.name
            }
        }
        _usersItemsView.value = UsersItemsView(
            isEmpty = false,
            users = processSuccessData(list ?: emptyList())
        )
        _isSorted.value = UsersSortedView(isSorted = true, sortedValue = filter)
        favoriteAction = !favoriteAction
    }

    fun setSortList(isSorted: Boolean) {
        _isSorted.value = UsersSortedView(isSorted = isSorted, sortedValue = "")

    }

    fun getUserByName(name: String) {
        val list = originalList.values.flatten()
            .filter { it.name.uppercase().contains(name.uppercase()) }
        _usersItemsView.value = UsersItemsView(
            isEmpty = false,
            users = processSuccessData(list)
        )
        favoriteAction = !favoriteAction
        nextPage(1)
    }

    fun getOriginalList() {
        _usersItemsView.value = UsersItemsView(
            isEmpty = false,
            users = originalList
        )
        favoriteAction = !favoriteAction
    }

    fun setFavoriteUser(user: DUser, isFavorite: Boolean) {
        val list = mutableListOf<DUser>()
        _usersItemsView.value?.users?.values?.forEach { users ->
            users.forEach { user ->
                list.add(user)
            }

        }
        list.map {
            if (it.id == user.id) {
                it.isFavorite = isFavorite
            }
        }

        _usersItemsView.value =
            UsersItemsView(
                favoriteAction = !favoriteAction,
                users = validateFavorites(list)
            )

        favoriteAction = !favoriteAction
    }

}