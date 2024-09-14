package com.alejandromejia.hellobuildtest.domain.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable

class ScrollStateManager {
    companion object {
        @Volatile
        private var instance: ScrollStateManager? = null

        fun getInstance(): ScrollStateManager =
            instance ?: synchronized(this) {
                instance ?: ScrollStateManager().also { instance = it }
            }
    }

    private val scrollStates = mutableMapOf<String, ScrollState>()

    @Composable
    fun getScrollState(key: String): ScrollState {
        return scrollStates.getOrPut(key) { rememberScrollState() }
    }
}