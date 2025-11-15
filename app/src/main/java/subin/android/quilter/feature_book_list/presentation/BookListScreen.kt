package subin.android.quilter.feature_book_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import subin.android.quilter.R
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.presentation.components.BookCard
import subin.android.quilter.feature_book_list.presentation.components.BookDetailBottomSheet
import subin.android.quilter.feature_book_list.presentation.components.BookListTabs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val selectedBook by viewModel.selectedBook.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${stringResource(id = R.string.app_name)} Open Library Books",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            BookListTabs(
                selectedTab = selectedTab,
                onTabSelected = { viewModel.onTabSelected(it) }
            )
            BookListContent(uiState) {
                viewModel.onBookClicked(it)
            }
        }

        if (selectedBook != null) {
            BookDetailBottomSheet(
                book = selectedBook!!,
                onDismiss = { viewModel.closeBookDetails() }
            )
        }
    }
}


@Composable
fun BookListContent(
    uiState: BookListUiState,
    onBookClicked: (Book) -> Unit
) {
    when (uiState) {
        is BookListUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("loading_box"),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is BookListUiState.Error -> {
            val message = uiState.message
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .testTag("error_box"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        is BookListUiState.Success -> {
            val books = uiState.books
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("success_list"),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(books, key = { it.id }) { book ->
                    BookCard(
                        book = book,
                        onClick = { onBookClicked(book) }
                    )
                }
            }
        }
    }
}
