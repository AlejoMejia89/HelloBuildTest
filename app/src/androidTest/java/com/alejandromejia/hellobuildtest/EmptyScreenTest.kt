package com.alejandromejia.hellobuildtest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alejandromejia.hellobuildtest.ui.main.components.EmptyScreen
import com.alejandromejia.hellobuildtest.utils.PROGRESS_TEXT
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmptyScreenUI() {
        composeTestRule.setContent {
            EmptyScreen(
                modifier = Modifier.fillMaxSize(),
                text = "empty",
                resource = R.drawable.ic_favorites_empty
            )
        }


        composeTestRule.onRoot().assertIsDisplayed()
        composeTestRule.onNodeWithText("empty").assertIsDisplayed()
    }

}