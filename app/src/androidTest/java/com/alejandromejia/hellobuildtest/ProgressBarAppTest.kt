package com.alejandromejia.hellobuildtest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import com.alejandromejia.hellobuildtest.ui.main.components.ProgressBarApp
import com.alejandromejia.hellobuildtest.utils.PROGRESS_TEXT
import org.junit.Rule
import org.junit.Test


class ProgressBarAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun progressBarApp_isDisplayed() {
        composeTestRule.setContent {
            ProgressBarApp()
        }
        composeTestRule.onRoot().assertIsDisplayed()
        composeTestRule.onNodeWithText(PROGRESS_TEXT).assertIsDisplayed()
    }

}