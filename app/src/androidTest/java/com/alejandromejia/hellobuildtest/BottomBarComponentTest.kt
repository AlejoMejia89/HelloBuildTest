package com.alejandromejia.hellobuildtest

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alejandromejia.hellobuildtest.domain.utils.BottomBarScreen
import com.alejandromejia.hellobuildtest.domain.utils.BottomBarScreen.ScreensList.screens
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BottomBarComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    @LargeTest
    fun testBottomBarNavigation() {
        val screens = BottomBarScreen.ScreensList.screens

        assertIndexIsCorrect(screens[0])
        assertIndexIsCorrect(screens[1])
    }

    private fun assertIndexIsCorrect(expectedIndex: BottomBarScreen) {
        val currentScreen = screens.find { it.selected == expectedIndex.selected }
        assert(currentScreen?.selected == expectedIndex.selected)
    }

}