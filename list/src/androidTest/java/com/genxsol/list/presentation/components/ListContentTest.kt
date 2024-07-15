package com.genxsol.list.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.genxsol.list.domain.model.ListProductsModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalFoundationApi::class)
    @Test
    fun listContentDisplaysCorrectly() {
        val productList = listOf(
            ListProductsModel(
                productId = "1",
                productImage = "image1",
                text = "Product 1",
                subText = "Description 1",
                review = "",
                questions = "",
                rating = ""
            ),
            ListProductsModel(
                productId = "2",
                productImage = "image2",
                text = "Product 2",
                subText = "Description 2",
                review = "",
                questions = "",
                rating = ""
            )
        )

        composeTestRule.setContent {
            ListContent(productList = productList)
        }

        // Verify if Product 1 and Product 2 texts are displayed
        composeTestRule.onNodeWithText("Product 1").assertExists()
        composeTestRule.onNodeWithText("Product 2").assertExists()
    }
}


