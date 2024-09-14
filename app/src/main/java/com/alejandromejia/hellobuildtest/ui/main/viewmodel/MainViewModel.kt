package com.alejandromejia.hellobuildtest.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandromejia.hellobuildtest.domain.utils.ScreenComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _screen: MutableLiveData<ScreenComponent> =
        MutableLiveData<ScreenComponent>()
    val screen: LiveData<ScreenComponent> get() = _screen

    private val _showBottomSheet: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>()
    val showBottomSheet: LiveData<Boolean> get() = _showBottomSheet

    fun showBottomSheet(isVisible: Boolean) {
        _showBottomSheet.value = isVisible
    }

    fun setScreen(screen: ScreenComponent) {
        _screen.value = screen
    }

}