package subin.android.quilter.feature_book

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.presentation.BookListContent
import subin.android.quilter.feature_book_list.presentation.BookListUiState

@RunWith(AndroidJUnit4::class)
class BookListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_isDisplayed() {
        composeTestRule.setContent {
            BookListContent(
                uiState = BookListUiState.Loading,
                onBookClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag("loading_box")
            .assertExists()
    }

    @Test
    fun errorState_showsErrorMessage() {
        val message = "Failed to load data"

        composeTestRule.setContent {
            BookListContent(
                uiState = BookListUiState.Error(message),
                onBookClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag("error_box")
            .assertExists()

        composeTestRule
            .onNodeWithText(message)
            .assertExists()
    }

    @Test
    fun successState_displaysBooks_andTriggersCallback() {
        val books = listOf(
            Book("1", "Book A", "Auth", 2020, 0, 0, "2025"),
            Book("2", "Book B", "Auth", 2021, 0, 0, "2025")
        )

        var clickedBook: Book? = null

        composeTestRule.setContent {
            BookListContent(
                uiState = BookListUiState.Success(books),
                onBookClicked = { clickedBook = it }
            )
        }

        composeTestRule.onNodeWithTag("success_list").assertExists()
        composeTestRule.onNodeWithText("Book A").assertExists()
        composeTestRule.onNodeWithText("Book B").assertExists()

        composeTestRule.onNodeWithText("Book A").performClick()

        assertEquals("1", clickedBook?.id)
    }
}
